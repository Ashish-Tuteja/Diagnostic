package com.auction.pro.queue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auction.pro.common.constants.CimbleConstants;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.device.dto.DeviceDto;
import com.auction.pro.device.service.base.DeviceService;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.vehicle.filter.VehicleFilter;
import com.auction.pro.vehicle.model.Ecu;
import com.auction.pro.vehicle.model.EcuController;
import com.auction.pro.vehicle.model.GlobalParameter;
import com.auction.pro.vehicle.service.base.VehicleService;

@Component
public class Listener implements CimbleConstants {
	@Autowired
	VehicleService vehicleService;
	@Autowired
	DeviceService deviceService;
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class
			.getName());
	String serverName;
	String vin;
	String url;
	int port = 1722;
	private Socket client;
	private BufferedWriter out;
	VehicleDto vehicleDTO = null;

	public void onMessage(String data) {
		// TODO Auto-generated method stub
		StringBuffer uuid = new StringBuffer();
		uuid.append(Calendar.getInstance().getTimeInMillis());
		String sessionId = Integer.toString((int) Math.round(Math.random() * 999999));
		LOGGER.info("SESSION ID: " + sessionId);
		
		try {
			vin = data.split(",")[0];
			serverName = data.split(",")[1];
			LOGGER.info("GET IP is  " + serverName);
			DeviceDto device = saveDevice(serverName);
			if (device == null) {
				LOGGER.info("Device already exists");
				device = deviceService.findById(serverName);
				System.out.println("Device already exists");
			} else {
				LOGGER.info("Device save");
				System.out.println("Device save");

			}

			vehicleDTO = vehicleService.findByVIN(vin);
			if (vehicleDTO == null) {
				url = "http://www.decodethis.com/webservices/API.ashx?vin="
						+ vin
						+ "&APIKey=8d04c7d0-a00f-4bc4-9cb9-99a3349d0846&format=JSON";
				JSONObject vehicleJSON = CommonUtils.readJsonFromUrl(url);
				vehicleDTO = new VehicleDto(vehicleJSON.getJSONObject("decode"));
				LOGGER.info("Report fetching");

				LOGGER.info("JSON OBJECT  "
						+ vehicleJSON.getJSONObject("decode"));
				if (vehicleJSON.getJSONObject("decode").toString() != null) {
					LOGGER.info("Save vehicle");
					saveVehicle(vehicleDTO, device);
				} else {
					LOGGER.error("Reponse not get from " + url);
				}

			} else {
				LOGGER.info("Vehicle already exists!!");
			}
			LOGGER.info("Send Data to device");
			try {
				VehicleFilter filter = new VehicleFilter();
				filter.setMake(vehicleDTO.getMake());
				filter.setYear(vehicleDTO.getYear());
				filter.setModel(vehicleDTO.getModel());
				LOGGER.info("make : " + vehicleDTO.getMake() + "\t year : " + vehicleDTO.getYear() + "\t model : " + vehicleDTO.getModel());

				Ecu ecu = vehicleService.getvehicleECU(filter);

				// get default ecu for Toyota-Camery-2011
				if (ecu == null) {
				    LOGGER.info("ECU not found for vehicle. Getting default.");
				    filter = new VehicleFilter();
	                	    filter.setMake(CimbleConstants.MAKE);
	                	    filter.setYear(CimbleConstants.YEAR);
	                	    filter.setModel(CimbleConstants.MODEL);
	                	    ecu = vehicleService.getvehicleECU(filter);
	                	    if (ecu == null) {
	                    		LOGGER.error("Default ecu not found");
	                    		return;
	                	    }
				}
				List<EcuController> controllers = ecu.getControllers();
				if (controllers == null || controllers.size() <= 0) {
					System.out.println("Controllers not found");
					return;
				}
				LOGGER.info("setting session id: " + sessionId);
				client = new Socket(serverName, port);
				//out = new DataOutputStream(client.getOutputStream());
				
				// create buffered writter to send data to device.
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        			out.write("I" + "," + sessionId + ":");
				out.flush();
				
				// wait before sending the next packet
				Thread.sleep(1000);
				
				try {
					// save session id before sending commands
					vehicleService.insertVehicleReportGroupId(sessionId, vin);
					LOGGER.info("vehicle -> groupId <- vehicleReport");
					vehicleService.updateTimeStamp(vin);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				for (EcuController ecuController : controllers) {
                    			LOGGER.info("Getting test parameters for controller : " + ecuController.getControllerId());
					// clear list after each controller
					List<GlobalParameter> globalParameters = null;
					globalParameters = vehicleService.getDataList(ecuController.getControllerId());
					// loop over all the parameters for a given controller
					for (GlobalParameter globalParameter : globalParameters) {

						LOGGER.info("Connecting....");
						LOGGER.info("Client is Running");
						//out = new DataOutputStream(client.getOutputStream());

						// 0 = PACKET TYPE (D or S) (STRING)
						// 1 = TEST ID (LONG)
						// 2 = TX_ID (HEX)
						// 3 = RX_ID (HEX)
						// 4 = ENHANCED (INT 0 or 1)
						// 5 = EXTENDED ID (HEX)
						// 6 = MODE/SERVICE_ID (HEX)
						// 7 = PID/PARAMETER_ID (HEX)
						// 8 = POSITION (INT)
						// 9 = WIDTH (INT)
						// 10 = LATENCY (INT)
                        String testStr = null;
                        
                        testStr = (String.valueOf(globalParameter.getIsEnhanced()).equals("1") ? "D" : "S") + ","
                                + globalParameter.getParameterDescId() + "," + ecuController.getTxId() + "," + ecuController.getRx() + ","
                                + globalParameter.getIsEnhanced() + "," + ecuController.getExtendedId() + ","
                                + globalParameter.getServiceId() + "," + globalParameter.getParameterId() + ","
                                + globalParameter.getBitpostion() + "," + globalParameter.getBitwidth() + ","
                                + ecuController.getWorstCaseLatency() + ":";
                        	LOGGER.info("Sending test string: " + testStr + " on device with ip " + serverName + ":" + port);
						out.write(testStr);
						out.flush();
						// pause before sending next command
						//Thread.sleep(500);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(serverName + " not Listen " + " on port " + port,
						e.getMessage());
			} finally {
				if (out != null) {
					out.close();
				}
				if (client != null) {
					client.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("Url return null ", e.getMessage());
		} catch (JSONException e) {
			e.printStackTrace();
			LOGGER.error("Data can't cast in JSON ", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Device problem ", e.getMessage());
		}

	}

	@SuppressWarnings("unused")
	private void onMessage(VehicleDto reportDto) {
		try {
			vehicleService.saveReport(reportDto);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			LOGGER.error("Report not save ", e.getMessage());
		}

	}

        /**
        public static long generateRandom(int length) {
    		Random random = new Random();
    		char[] digits = new char[length];
    		digits[0] = (char) (random.nextInt(9) + '1');
    		for (int i = 1; i < length; i++) {
        		digits[i] = (char) (random.nextInt(10) + '0');
    		}
    		return Long.parseLong(new String(digits));
	}
	**/

	private DeviceDto saveDevice(String servername) throws Exception {
		DeviceDto deviceDto = new DeviceDto();
		deviceDto.setDeviceName("device_" + servername);
		deviceDto.setIp(servername);
		return deviceService.save(deviceDto);

	}

	private VehicleDto saveVehicle(VehicleDto vehicleDTO, DeviceDto deviceDTO) {
		vehicleDTO.setDeviceId(deviceDTO.getId());
		vehicleDTO.setDeviceName(deviceDTO.getDeviceName());
		return vehicleService.save(vehicleDTO);
	}
}
