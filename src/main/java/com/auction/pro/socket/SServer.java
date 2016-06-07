package com.auction.pro.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

public class SServer {

	public static final int PORT = 1722;
	private static final Logger log = LoggerFactory.getLogger(SServer.class);
	byte[] buf = new byte[2048];

	public static void main(String[] args) {
		new SServer().startSServer();
	}

	public void startSServer() {
		final ExecutorService clientProcessingPool = Executors
				.newFixedThreadPool(1);

		Runnable SServerTask = new Runnable() {
			public void run() {
				try {
					
					@SuppressWarnings("resource")
					ServerSocket serverSocket = new ServerSocket(SServer.PORT);

					log.debug("Waiting for a device to connect...");
					System.out.println("waiting to send packet after running tests");
					while (true) {
						Socket clientSocket = serverSocket.accept();
						System.out.println("received packet from device ");
						log.debug("received data");
						clientProcessingPool.submit(new ClientTask2(
								clientSocket));
					}

				} catch (IOException e) {
					log.error("Unable to process client request. "
							+ e.getMessage());
				}
			
				try {
					
					@SuppressWarnings("resource")
					ServerSocket serverSocket = new ServerSocket(SServer.PORT);

					log.debug("Waiting for a device to connect...");
					System.out.println("waiting to send packet after running tests");
					while (true) {
						Socket clientSocket = serverSocket.accept();
						System.out.println("received packet from device ");
						log.debug("received data");
						clientProcessingPool.submit(new ClientTask2(
								clientSocket));
					}

				} catch (IOException e) {
					log.error("Unable to process client request. "
							+ e.getMessage());
				}
			}
		};
		Thread SServerThread = new Thread(SServerTask);
		SServerThread.start();

	}

	private class ClientTask2 implements Runnable {
		private Socket clientSocket;

		private ClientTask2(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			
			System.out.println("Got a client connection:  in SServer"
					+ new Date(System.currentTimeMillis()).toString());
			log.debug("Got a client connection: "
					+ new Date(System.currentTimeMillis()).toString());
			try {
				// Get IP Address
				String ip = clientSocket.getInetAddress().getHostAddress()
						.toString();
				System.out.println("Connection IP: " + ip + " "
						+ clientSocket.getInputStream().available());
				log.debug("Connection IP: " + ip + " "
						+ clientSocket.getInputStream().available());
				clientSocket = new Socket(InetAddress.getLocalHost().toString()
						.split("/")[1], 1721);
				OutputStream outToServer = clientSocket.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
				System.out.println();
				out.write(" 2,0,2,P0403;P0203"
						.getBytes());
				System.out.println("Send Data.... ");
				// out.flush();
				clientSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
