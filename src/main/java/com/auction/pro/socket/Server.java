package com.auction.pro.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	
	public static final int PORT = 1721;
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class
			.getName());
	byte[] buf = new byte[2048];
	public static final String SYSTEM_REPORT = "System Report";
	public static final String DYNAMIC_REPORT = "Dynamic Report";
	public static final String TROUBLE_CODE = "Trouble Code";
	public static final String STATIC_REPORT = "Static Report";
	public static final String STATIC_RESULT_REPORT = "Static Test Report";
	public static final String DYNAMIC_RESULT_REPORT = "Dynamic Test Report";
	public static final String ODOMETER_REPORT = "Odometer Report";
	public static final String DUMP = "Dump";

	public static void main(String[] args) {
		System.out.println();
		new Server().startServer();
	}

	public void startServer() {
		final ExecutorService clientProcessingPool = Executors
				.newFixedThreadPool(10);

		Runnable serverTask = new Runnable() {

			public void run() {
				try {
					ServerSocket serverSocket = new ServerSocket(Server.PORT);
					serverSocket.setSoTimeout(0);
					System.out.println("client not connected yet");
					LOGGER.debug("Waiting for a device to connect...");
					while (true) {
						Socket clientSocket = serverSocket.accept();
						System.out.println("connect to client    1");
						LOGGER.debug("received data");
						clientProcessingPool.execute(new ClientTask(
								clientSocket));
					}

				} catch (IOException e) {
					LOGGER.error("Unable to process client request. "
							+ e.getMessage());

				}
			}
		};
		Thread serverThread = new Thread(serverTask);
		serverThread.start();

	}

	private class ClientTask implements Runnable {
		private final Socket clientSocket;

		private ClientTask(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			long start = System.currentTimeMillis();
			System.out.println("Got a client connection:  2"
					+ new Date(System.currentTimeMillis()).toString());
			LOGGER.debug("Got a client connection: "
					+ new Date(System.currentTimeMillis()).toString());

			// getTimeStamp
			java.sql.Timestamp time = new java.sql.Timestamp(
					System.currentTimeMillis());

			try {
				// Get IP Address
				String ip = clientSocket.getInetAddress().getHostAddress()
						.toString();
				System.out.println("Connection IP: " + ip);
				LOGGER.debug("Connection IP: " + ip);

				// Store Payload
				String payload = "";
				String input = "";
				String line = "";
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream(),
								"UTF-8"));
				try {
					
					System.out.println("reading each string");
					while ((line = reader.readLine()) != null) {
						input = input + line;
						System.out.println(input);
					}
				} catch (Exception se) {
					// Unable to read send error message.
					LOGGER.debug(" New packet recieved: "
							+ new Date(System.currentTimeMillis()).toString());
					LOGGER.info("Packet Size: " + input);
					se.printStackTrace();
				} finally {
					reader.close();
					clientSocket.close();
				}

				
				System.out.println(" New packet recieved: " + time.toString());
				System.out.println("============================="+ "Packet Size: " + input.length());
				LOGGER.debug(" New packet recieved: " + time.toString());
				LOGGER.debug("Packet Size: " + input.length());

				payload = new String(input).trim(); // All other packets will
				// use this format.
				System.out.println("Payload: " + payload);
				LOGGER.debug("Payload: " + payload);

				/**
				 * 1 = VIN Registration 2 = Trouble Code Report 3 = Static Data
				 * 4 = Dynamic Data Report 5 = Result from static test 6 =
				 * Result from dynamic test 7 = Odometer in tenths of a
				 * kilometer 8 = Unknown
				 **/
				String[] packetList = payload.split(",");
				LOGGER.debug("Code is :::: " + packetList[0]);
				int packetType = Integer.parseInt(packetList[0]);
				if ("1".equals(packetList[0])) {
					String vin = packetList[1].toString();
					System.out.println("New VIN: " + vin);
					LOGGER.debug("New VIN: " + vin);
					String url = "http://localhost:8080/NavResearch/vehicle/savevehicle";
					String postjson = "{\"vin\":\"" + vin
							+ "\",\"serverIP\":\"" + ip + "\"}";
					sendPost(url, postjson);
				} else if ("2".equals(packetList[0])) {
					LOGGER.debug("Trouble Code Report: ");
					sendReport(packetList, TROUBLE_CODE, ip, packetType);
				} else if ("3".equals(packetList[0])) {
					LOGGER.debug("Static Data Report");
					sendReport(packetList, STATIC_REPORT, ip, packetType);
				} else if ("4".equals(packetList[0])) {
					LOGGER.debug("Dynamic Data Report");
					sendReport(packetList, DYNAMIC_REPORT, ip, packetType);
				} else if ("5".equals(packetList[0])) {
					LOGGER.debug("Static Test Result response from test packet");
					sendReport(packetList, STATIC_REPORT, ip, packetType);
				} else if ("6".equals(packetList[0])) {
					LOGGER.debug("Dynamic Test Result response from test packet");
					sendReport(packetList, DYNAMIC_REPORT, ip, packetType);
				} else if ("7".equals(packetList[0])) {
					LOGGER.debug("Odometer response");
					sendReport(packetList, ODOMETER_REPORT, ip, packetType);
				} else {
					LOGGER.info("unknown code - " + packetList[0]);
					sendReport(packetList, DUMP, ip, packetType);
				}

				LOGGER.debug("Amount of time to enqueue message: "
						+ (System.currentTimeMillis() - start) + "ms\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void sendPost(String url, String postjson) {
			try {
				HttpURLConnection httpConnection = (HttpURLConnection) new URL(
						url).openConnection();

				LOGGER.info("buildserver Url: " + url);
				LOGGER.info("dataToPost: " + postjson);

				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("charset", "utf-8");
				httpConnection.setRequestProperty("Content-Type",
						"application/json");

				// no caching...
				httpConnection.setUseCaches(false);
				httpConnection.setDoInput(true);
				httpConnection.setDoOutput(true);

				// needed for SSL
				httpConnection.connect();

				// Send POST request
				DataOutputStream wr = new DataOutputStream(
						httpConnection.getOutputStream());
				wr.writeBytes(postjson);
				wr.flush();

				BufferedReader rd = new BufferedReader(new InputStreamReader(
						httpConnection.getInputStream()));
				String response = rd.readLine();

				// close everything....
				wr.close();
				rd.close();
				System.out.println(("response = " + response));
				LOGGER.debug("status " + httpConnection.getResponseCode());

				// disconnect it...
				httpConnection.disconnect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		private void sendReport(String[] packetList, String reportname,
				String ip, int packetType) throws UnsupportedEncodingException,
				IOException, ClientProtocolException {
			if (((Long) Long.parseLong(packetList[2])).compareTo(0l) <= 0) {
				LOGGER.debug("Not saving to DB, sample size is 0");
				return;
			}
			System.out.println("Packet list -=======>"+ packetList);
			LOGGER.debug("Send data " + packetList[0]);

			if (packetList.length >= 4) {
				String url = "http://localhost:8080/NavResearch/vehicle/setreport";
				String postjson = "{\"reportname\":\"" + reportname
						+ "\",\"serverIP\":\"" + ip + ""
						+ "\",\"reportgroupId\":\"" + packetList[1].toString()
						+ "\",\"report\":\"" + packetList[3]
						+ "\",\"packetType\":" + packetType + "}";
				System.out.println("Sending report to get saved in mongodb");
				sendPost(url, postjson);
			}
		}
	}
}
