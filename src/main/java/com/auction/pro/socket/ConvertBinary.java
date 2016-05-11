package com.auction.pro.socket;

import java.math.BigInteger;

public class ConvertBinary {

	public static final double byte2Double(byte[] inData, int start,
			boolean byteSwap) {
		int j = start;

		int upper, lower;
		double outData = 0.0;
		if (!byteSwap) {
			upper = (((inData[j] & 0xff) << 24)
					+ ((inData[j + 1] & 0xff) << 16)
					+ ((inData[j + 2] & 0xff) << 8) + ((inData[j + 3] & 0xff) << 0));
			lower = (((inData[j + 4] & 0xff) << 24)
					+ ((inData[j + 5] & 0xff) << 16)
					+ ((inData[j + 6] & 0xff) << 8) + ((inData[j + 7] & 0xff) << 0));
			outData = Double.longBitsToDouble((((long) upper) << 32)
					+ (lower & 0xffffffffl));
		} else {
			upper = (((inData[j + 7] & 0xff) << 24)
					+ ((inData[j + 6] & 0xff) << 16)
					+ ((inData[j + 5] & 0xff) << 8) + ((inData[j + 4] & 0xff) << 0));
			lower = (((inData[j + 3] & 0xff) << 24)
					+ ((inData[j + 2] & 0xff) << 16)
					+ ((inData[j + 1] & 0xff) << 8) + ((inData[j] & 0xff) << 0));
			outData = Double.longBitsToDouble((((long) upper) << 32)
					+ (lower & 0xffffffffl));
		}
		return outData;
	}

	public static final int byte2Int(byte[] inData, int start, boolean byteSwap) {
		int j = start;
		// int value;
		int outData = 0;
		if (!byteSwap) {
			outData = (((inData[j] & 0xff) << 24)
					+ ((inData[j + 1] & 0xff) << 16)
					+ ((inData[j + 2] & 0xff) << 8) + ((inData[j + 3] & 0xff) << 0));
		} else {
			outData = (((inData[j + 3] & 0xff) << 24)
					+ ((inData[j + 2] & 0xff) << 16)
					+ ((inData[j + 1] & 0xff) << 8) + ((inData[j] & 0xff) << 0));
		}
		;
		return outData;
	}

	// byte2Short method - extracts short ints from byte array
	public static final short byte2Short(byte[] inData, int start,
			boolean byteSwap) {
		int j = start;
		short outData = 0;
		if (!byteSwap)
			outData = (short) ((inData[j] << 8) + (inData[j + 1] & 0xff));
		else
			outData = (short) (((inData[j + 1] & 0xff) << 8) + ((inData[j] & 0xff) << 0));
		return outData;
	}

	public static final String byte2String(byte[] inData, int start, int length) {
		// int j = start;
		String str = "";
		for (int i = 0; i < length; i++) {
			str += (char) inData[start + i];
		}
		return str;
	}

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	public static String asHex(byte[] buf) {
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; ++i) {
			chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
		}
		return new String(chars);
	}

	public static final String toHexString1(byte[] data) {
		// Create a BigInteger using the byte array
		BigInteger bi = new BigInteger(data);

		// Format to binary
		String s = bi.toString(16);
		// if (data.length % 2 != 0) {
		// // Pad with 0
		// s = "0"+s;
		// }
		return s;
	}

	public static final String toHexString(byte[] data) {
		// Create a BigInteger using the byte array
		BigInteger bi = new BigInteger(data);

		// Format to binary
		String s = bi.toString(16);
		if (data.length % 2 != 0) {
			// Pad with 0
			s = "0" + s;
		}
		return s;
	}

	public static final byte[] fromHexString(String s) {
		byte bytes[] = new byte[s.length() / 2];

		for (int i = 0; i < s.length() / 2; i++) {
			bytes[i] = (byte) (Integer.parseInt(s.substring(i * 2, i * 2 + 2),
					16));
		}

		return bytes;
	}

}
