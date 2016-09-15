
package com.auction.pro.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*public class Client {
    static Socket client = null;

    public static void main(String[] args) throws IOException {
            String serverName;
            int port = Integer.parseInt(args[1]);
            try {
                    serverName=args[0];//InetAddress.getLocalHost().toString().split("/")[1];
            //      serverName="localhost";
                    System.out.println("Connecting to " + serverName + " on port "
                                    + port);
                    client = new Socket(serverName, port);
                    System.out.println("Just connected to "
                                    + client.getRemoteSocketAddress());
                    // set output stream
                    OutputStream outToServer = client.getOutputStream();
                    DataOutputStream out = new DataOutputStream(outToServer);
                    out.write(args[2].getBytes());
                    out.flush();
                    //client.close();
            } catch (IOException e) {
                    e.printStackTrace();
                    client.close();
            }
    }
}

*/

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
			out.write("1,JN8AZ1MW1EW533613".getBytes());
			out.flush();
			// client.close();
		} catch (IOException e) {
			e.printStackTrace();
			client.close();
		}
	}
}