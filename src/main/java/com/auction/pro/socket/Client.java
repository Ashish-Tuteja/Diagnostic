package com.auction.pro.socket;

import java.net.*;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
	private static Socket client = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class
			.getName());

	public static void main(String[] args) throws IOException {
		String serverName;
		int port = Integer.parseInt("1721");
		try {
			serverName = InetAddress.getLocalHost().toString().split("/")[1];
			LOGGER.debug("Connecting to " + serverName + " on port " + port);
			client = new Socket("localhost", port);
			LOGGER.info("Just connected to " + client.getRemoteSocketAddress());
			// set output stream
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.write("1,5TFEM5F12AX002638".getBytes());
			out.flush();
			// client.close();
		} catch (IOException e) {
			e.printStackTrace();
			client.close();
		}
	}
}
