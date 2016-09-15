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
import com.auction.pro.vehicle.model.CanParameters;
import com.auction.pro.vehicle.model.EcuControllers;
import com.auction.pro.vehicle.model.GlobalParameters;
import com.auction.pro.vehicle.model.Manufacturer;
import com.auction.pro.vehicle.service.base.VehicleService;

@Component
public class Listener implements NavResearchConstants {
	@Autowired
	VehicleService vehicleService;
	@Autowired
	DeviceService deviceService;

	private static final Logger LOGGER =
		LoggerFactory.getLogger(Listener.class.getName());

	String serverName;
	String vin;
	String url;
	int port = 1722;
	private Socket client;
	private BufferedWriter out;
	int packetLength = 0;
	int previouspacketLength = 0;
	private static int  count = 0;
	boolean globalParameterMaxSize = false;

	public void onMessage(String data) {

		VehicleDto vehicleDTO = null;
		List<GlobalParameters> globalParameterList = null;
		List<CanParameters> canParameterList = null;

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

			LOGGER.info("setting session id: " + sessionId);
			client = new Socket(serverName, port);
			LOGGER.info("Client --->>>>" + client);

			vehicleDTO = vehicleService.findByVIN(vin);
			if (vehicleDTO == null) {
//				url = "http://www.decodethis.com/webservices/API.ashx?vin="
//						+ vin
//						+ "&APIKey=8d04c7d0-a00f-4bc4-9cb9-99a3349d0846&format=JSON";
				url = "https://api.edmunds.com/api/vehicle/v2/vins/"
						+ vin
						+ "?fmt=json&api_key=t2jjf6ptb2nrhfbvygnyyjhf";
				JSONObject vehicleJSON = CommonUtils.readJsonFromUrl(url);
				vehicleDTO = new VehicleDto(vehicleJSON, vin);

				LOGGER.info("JSON OBJECT  "
						+ vehicleJSON);
				if (vehicleJSON.toString() != null) {
					LOGGER.info("NEW: saving vehicle");
					saveVehicle(vehicleDTO, device);
				} else {
					LOGGER.error("Response not get from " + url);
				}
			}

			int makeId = 0; // NO MAKE FOUND
			if ( vehicleDTO.getMake().equalsIgnoreCase("gmc") ||
					vehicleDTO.getMake().equalsIgnoreCase("chevrolet") ||
					vehicleDTO.getMake().equalsIgnoreCase("buick") ||
					vehicleDTO.getMake().equalsIgnoreCase("cadillac") ||
					vehicleDTO.getMake().equalsIgnoreCase("gm") ) {
				// GM
				makeId = 1;
			} else if (vehicleDTO.getMake().equalsIgnoreCase("ford") ||
						vehicleDTO.getMake().equalsIgnoreCase("lincoln") ||
						vehicleDTO.getMake().equalsIgnoreCase("mercury") ) {
				// FORD
				makeId = 2;
			} else if (vehicleDTO.getMake().equalsIgnoreCase("chrysler") ||
						vehicleDTO.getMake().equalsIgnoreCase("dodge") ||
						vehicleDTO.getMake().equalsIgnoreCase("plymouth") ||
						vehicleDTO.getMake().equalsIgnoreCase("jeep") ) {
				// Chrysler, Dodge, Plymouth, Jeep
				makeId = 3;
			} else if (vehicleDTO.getMake().equalsIgnoreCase("toyota") ||
						vehicleDTO.getMake().equalsIgnoreCase("lexus") ||
						vehicleDTO.getMake().equalsIgnoreCase("scion") ) {
				// Toyota, Lexus, Scion
				makeId = 4;
			} else if (vehicleDTO.getMake().equalsIgnoreCase("nissan") ||
						vehicleDTO.getMake().equalsIgnoreCase("infiniti") ) {
				// Nissan, Infiniti
				makeId = 5;
			} else if (vehicleDTO.getMake().equalsIgnoreCase("honda") ||
						vehicleDTO.getMake().equalsIgnoreCase("acura") ) {
				// Honda, Acura
				makeId = 6;
			} else if (vehicleDTO.getMake().equalsIgnoreCase("hyundai") ||
						vehicleDTO.getMake().equalsIgnoreCase("kia") ) {
				// Hyundai, Kia
				makeId = 7;
			} else {
				// Unknown
				makeId = 0;
			}


			String info = "I" + "," + sessionId + "," + makeId + ":";
			packetLength = info.getBytes().length;

			// create buffered writer to send data to device.
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			out.write(info);
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
				LOGGER.error("Unable to save vehicle sessionID " + sessionId + " vin: " + vin, e.getMessage());
			}


			// get controllers based on make , model , year from vehicle
			EcuControllers ecus = vehicleService.getvehicleECU(
					vehicleDTO.getMake(), vehicleDTO.getModel(),vehicleDTO.getYear());

			if (ecus != null) {

					LOGGER.info("Ecus return for vehicle" + "Make : "
							+ ecus.getMake() + "  Model :" + ecus.getModel()
							+ "  Year :" + ecus.getYear() + "ControllerId : "
							+ ecus.getControllerId());

					try {
						LOGGER.info("Getting parameter_tests for controller : " + ecus.getControllerId());
						globalParameterList = vehicleService.getDataList(ecus.getControllerId());

						LOGGER.info("Send test data to device");
						LOGGER.info("Printing for next strings where count is " + count);

						String testStr = null;

						// loop over all the parameters for a given controller
						for (GlobalParameters globalParameter : globalParameterList) {

							LOGGER.info("Current test count:" + count);

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

							// reset before sending next command
		        	 testStr = null;

							 testStr =
							 	"E,D"
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

								// send parameter test string
								sendPacket(testStr);

						}

					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error(serverName + " not Listen " + " on port " + port, e.getMessage());
					}
				}

				LOGGER.info("Check for the CAN Parameters for " + vehicleDTO.getMake());

				//getting manufacturerId from make
				Manufacturer manufacturer =
					vehicleService.getManufacturerByMake(vehicleDTO.getMake());

				if(manufacturer == null) {
					LOGGER.info("No manufacturer found for " + vehicleDTO.getMake());
					LOGGER.info("All tests are complete. Send termination string.");
					sendPacket("X");
					return;
				}

				LOGGER.info("Manufacturer ID: " + manufacturer.getManufacturerId());

				// get CanParameters based on manufacturerId from vehicle
				canParameterList = vehicleService.getCanParameters(
						manufacturer.getManufacturerId());
				LOGGER.info("canList size:" + canParameterList.size());

				if (canParameterList == null || canParameterList.size() == 0) {
					LOGGER.info("No CAN Parameters exist for this vehicle (Make)");
					LOGGER.info("All tests are complete. Send termination string.");
					sendPacket("X");
					return;
				} else {

					try {

						LOGGER.info("Send test data to device");
						LOGGER.info("Printing for next strings where count is " + count);

						// loop over all the CAN parameters for a given make
						for (CanParameters cps : canParameterList) {

							LOGGER.info("Current test count:" + count);

							// 0 - MESSAGE_SOURCE         'C' for native CAN Message to monitor
							// 1 - PACKET_TYPE            'D' for Dynamic; 'S' for Static // at one time, I thought that all dynamic was right, but changed my mind and static is also the best for odometer
							// 2 - CAN_FUNCTION_ID        Value gets the data channeled to be interpreted and formated
							// 3 - CAN_MSGID              CAN message to monitor for values
							// 4 - CAN_MULTIPLEXID        0 - used when one MsgID gets used for several message types
							// 5 - CAN_MULTIPLEXMASK    	 bits to mask in data word to get only the multiplex value
							// 6 - CAN_ISLITTLEENDIAN     0 for big endian, 1 for little endian
							// 7 - CAN_BITPOSITION        starting bit in data words for value
							// 8 - CAN_BITCOUNT           number of bits in value

							 String testStr = null;
							 testStr =
							 	"C," 											// message source CAN
								+cps.getPacketType() 			// static or dynamic types
								+ ","
							 	+cps.getFunctionId() 			// should use 3000-5999 block
							 	+ ","
							 	+cps.getMessageId() 			// the CAN message to monitor
							 	+ ","
							 	+ cps.getMultiplexId() 		// used to support multiple message types
								+ ","
								+ cps.getMultiplexMask() 	// bits to mask in data word
								+ ","
								+ cps.getIsLittleEndian() // 0 for big endian, 1 for little endian
								+ ","
							 	+cps.getBitPosition() 		// starting bit in data words for value
								+ ","
								+ cps.getBitCount()				// number of bits in value
								+ ":"; 										// CLOSING DELIMITER

							  LOGGER.info("CAN test String: "+ testStr);

							  sendPacket(testStr);

						}

						LOGGER.info("All tests are complete. Send termination string.");
						sendPacket("X");

					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error(serverName + " not Listen " + " on port " + port, e.getMessage());
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
		} finally {
			LOGGER.error("Finished processing all parameter tests.");
			try {
				if (out != null) {
					out.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unused")
	private void onMessage(VehicleDto reportDto) {
		try {
			// wait before sending the next packet
			Thread.sleep(3000);
			LOGGER.info("After sending all test Strings , finally saving report !! ");
			vehicleService.saveReport(reportDto);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			LOGGER.error("Report not saved ", e.getMessage());
		}

	}


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

	private void sendPacket(String testStr)
		throws IOException , JSONException, InterruptedException{

		previouspacketLength = packetLength;
		packetLength = testStr.getBytes().length;
		count ++;
		packetLength = packetLength + previouspacketLength;
		LOGGER.info("Current bytes count is " + packetLength);

		if(packetLength >= 1020) {

			packetLength = 0;
			previouspacketLength = 0;

			LOGGER.info("Exceeded Packet Size, reset byte count:" + packetLength);

			// Start a new packet length with the of good.
			packetLength = testStr.getBytes().length;

			LOGGER.info("New byte count:" + packetLength);

			try {
				if (out != null) {
					out.close();
					LOGGER.info("Out closed");
				}
				if (client != null) {
					client.close();
					LOGGER.info("Client closed ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			Thread.sleep(3000);

			LOGGER.info("Creating a new socket connection");
			client = new Socket(serverName, port);
			out = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));

			LOGGER.info("Sending test string: " + testStr + " to device with ip " + serverName);
			try {
				if (out == null) {
					client = new Socket(serverName, port);
					out = new BufferedWriter(new OutputStreamWriter(
							client.getOutputStream()));
				}
				out.write(testStr);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Unable to send test string: " + testStr + " to device with ip " + serverName);
			}

		} else {
			LOGGER.info("Sending test string: " + testStr + " to device with ip " + serverName);
			try {
				if (out == null) {
					client = new Socket(serverName, port);
					out = new BufferedWriter(new OutputStreamWriter(
							client.getOutputStream()));
				}
				out.write(testStr);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Unable to send test string: " + testStr + " to device with ip " + serverName);
			}
		}
	}
}
