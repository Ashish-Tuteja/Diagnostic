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

import com.auction.pro.common.constants.NavResearchConstants;
import com.auction.pro.common.utils.CommonUtils;
import com.auction.pro.device.dto.DeviceDto;
import com.auction.pro.device.service.base.DeviceService;
import com.auction.pro.vehicle.dto.VehicleDto;
import com.auction.pro.vehicle.model.EcuControllers;
import com.auction.pro.vehicle.model.GlobalParameters;
import com.auction.pro.vehicle.service.base.VehicleService;

@Component
public class Listener implements NavResearchConstants {
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
	List<GlobalParameters> globalParameterList = null;

	public void onMessage(String data) {
		// TODO Auto-generated method stub
		StringBuffer uuid = new StringBuffer();
		uuid.append(Calendar.getInstance().getTimeInMillis());
		String sessionId = Integer
				.toString((int) Math.round(Math.random() * 999999));
		LOGGER.info("SESSION ID: " + sessionId);

		try {
			vin = data.split(",")[0];
			serverName = data.split(",")[1];
			LOGGER.info("GET IP is  " + serverName);
			DeviceDto device = saveDevice(serverName);
			if (device == null) {
				LOGGER.info("Device already exists");
				device = deviceService.findById(serverName);
			} else {
				LOGGER.info("Device save");
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
			// get controllers based on make , model , year from vehicle
			EcuControllers ecus = vehicleService.getvehicleECU(
					vehicleDTO.getMake(), vehicleDTO.getModel(),
					vehicleDTO.getYear());

			if (ecus == null) {
				LOGGER.info("No Ecus exist for this vehicle");
				LOGGER.error("VIN should match with ecu_controller(Make , model , year)");
				return;
			} else {
				LOGGER.info("Ecus return for vehicle" + "Make : "
						+ ecus.getMake() + "  Model :" + ecus.getModel()
						+ "  Year :" + ecus.getYear() + "ControllerId : "
						+ ecus.getControllerId());
			}

			try {

				LOGGER.info("setting session id: " + sessionId);
				client = new Socket(serverName, port);
				System.out.println("Client --->>>>" + client);
				// out = new DataOutputStream(client.getOutputStream());
				// create buffered writer to send data to device.
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						client.getOutputStream()));
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

				LOGGER.info("Getting parameter_tests for controller : "
						+ ecus.getControllerId());
				globalParameterList = vehicleService.getDataList(ecus
						.getControllerId());
				// loop over all the parameters for a given controller
				for (GlobalParameters globalParameter : globalParameterList) {
					LOGGER.info("Connecting....");
					LOGGER.info("Client is Running");
					// out = new DataOutputStream(client.getOutputStream());

					/** Old Format deprecated as of 04-30-2016 **/
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
					
					/** New Format as of 05-01-2016 **/
					// SOURCE (C/E)
					// PACKET TYPE (D/S)
					// SESSION ID (LONG)
					// TX_ID (HEX)
					// RX_ID (HEX)
					// MODE/SERVICE_ID (HEX)
					// PID/MESSEGE_ID (HEX)
					// POSITION (INT)
					// WIDTH (INT)
					// OFFSET (INT)
					// ENDIANESS (INT)

                    String testStr = null;
                    
					 testStr = 
					 	(String.valueOf(globalParameter.getIsEnhanced()).equals("1") ? "E" : "C") // SOURCE
						+ ","
					 	+ (String.valueOf(globalParameter.getIsEnhanced()).equals("1") ? "D" : "S") // PACKET TYPE
						+ ","
					 	+ globalParameter.getParameterDescId()// UNIQUE ID FOR PARAMETER 
						+ "," 
						+ globalParameter.getTxId() // TX ID
						+ "," 
						+ globalParameter.getRxId() // RX ID
						+ ","
						+ globalParameter.getServiceId() // SERVICE ID/MODE
						+ "," 
						+ globalParameter.getParameterId() // PARAMETER ID
						+ ","
						+ globalParameter.getBitpostion() // POSITION
						+ "," 
						+ globalParameter.getBitwidth() // WIDTH
						+ ","
						+ globalParameter.getOffset() // OFFSET
						+ "," 
						+ globalParameter.getEndness() // ENDINESS
						+ ":"; // CLOSING DELIMITER
						
					LOGGER.info("Sending test string: " + testStr + " to device with ip " + serverName + ":" + port);
					out.write(testStr);
					out.flush();
					// pause before sending next command
					// Thread.sleep(500);
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
	 * public static long generateRandom(int length) { Random random = new
	 * Random(); char[] digits = new char[length]; digits[0] = (char)
	 * (random.nextInt(9) + '1'); for (int i = 1; i < length; i++) { digits[i] =
	 * (char) (random.nextInt(10) + '0'); } return Long.parseLong(new
	 * String(digits)); }
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
