package com.auction.pro.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auction.pro.common.constants.CimbleConstants;
import com.google.gson.Gson;

/**
 * @author infoobjects
 *
 */
public class CommonUtils implements CimbleConstants {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommonUtils.class.getName());

	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static <E, T> void printArray(E dest, T source)
			throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(dest, source);
	}

	/* Convert Pojo into JSON */
	public static <E> JSONObject convertPojoToJSon(E className) {
		JSONObject convertPojoToJSon = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			convertPojoToJSon = new JSONObject(
					mapper.writeValueAsString(className));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return convertPojoToJSon;
	}

	public static <E> String convertListsToJSon(List<E> users) {
		Gson gson = new Gson();
		return gson.toJson(users);
	}

	public static Properties getProperty(String filename) throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = CommonUtils.class.getClassLoader()
				.getResourceAsStream(filename);

		if (inputStream != null) {
			prop.load(inputStream);
			return prop;
		} else {
			throw new FileNotFoundException("property file '" + filename
					+ "' not found in the classpath");
		}
	}

	public static String md5String(String string) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer md5 = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				md5.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return md5.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static boolean checkIsUsernameOrEmailId(String user) {
		// TODO Auto-generated method stub
		try {
			return user.matches(EMAIL_PATTERN);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @param string
	 * @return generate hash(use of BCryptPasswordEncoder) of a string
	 */
	public static String encryption(String string) {
		return new BCryptPasswordEncoder().encode(string);
	}

	/**
	 * @param url
	 * @return JSONObject
	 * @throws IOException
	 * */
	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = null;
		LOGGER.info("Fetch url is " + url);
		try {
			is = new URL(url).openStream();
			Reader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			JSONObject json = new JSONObject(sb.toString());
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			is.close();
		}
	}

	/**
	 * @param file
	 *            ,uploadoption
	 * @return {@link Map}
	 * @throws {@link IOException},{@link InvalidFormatException}
	 * */
	public static Map<Integer, List<String>> readExcel(File file,
			String uploadoption) throws IOException, InvalidFormatException {
		Map<Integer, List<String>> batchcollection = new HashMap<Integer, List<String>>();
		Workbook workbook;
		Row row = null;
		int numberofcells = 0;
		Sheet sheet = null;
		workbook = WorkbookFactory.create(file);
		sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		// skip first row
		row = rowIterator.next();
		boolean isFormat = false;
		try {
			isFormat = checkExcelFormat(row, uploadoption);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.error("Excel file not properly make");
		}
		if (!isFormat) {
			return null;
		}
		numberofcells = row.getLastCellNum() - row.getFirstCellNum();
		while (rowIterator.hasNext()) {
			List<String> columnValue = new LinkedList<String>();
			row = rowIterator.next();
			for (int cn = 0; cn <= numberofcells; cn++) {
				Cell cell = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
				if (cell == null) {
					columnValue.add("");
				} else {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						columnValue.add(String.valueOf(cell
								.getStringCellValue()));
						break;
					case Cell.CELL_TYPE_NUMERIC:
						columnValue.add(String.valueOf((long) cell
								.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						columnValue.add(String.valueOf(cell
								.getBooleanCellValue()));
						break;
					case Cell.CELL_TYPE_FORMULA:
						columnValue.add(String.valueOf(cell
								.getStringCellValue()));
						break;
					default:
						LOGGER.info("Not supported");

					}
				}
			}
			batchcollection.put(row.getRowNum(), columnValue);
		}
		return batchcollection;
	}

	/**
	 * @param firstRow
	 *            ,uploadoption
	 * @throws Exception
	 * @return {@link Boolean}
	 * */
	private static boolean checkExcelFormat(Row firstRow, String uploadoption)
			throws Exception {
		if (uploadoption.equalsIgnoreCase(GLOBAL_PARAMETER_TABLE_NAME)) {
			return firstRow.getLastCellNum() == GLOBAL_PARAMETER_TABLE_SIZE
					&& checkExcelColumnName(firstRow, GLOBAL_PARAMTER_TABLE) ? true
					: false;
		} else if (uploadoption.equalsIgnoreCase(VEHICLEECU_TABLE_NAME)) {
			return true;
		} else if (uploadoption.equalsIgnoreCase(GLOBALECU_TABLE_NAME)) {
			return firstRow.getLastCellNum() == GLOBALECU_TABLE_SIZE ? true
					: false;
		} else {
			LOGGER.info("Not a file");
			return false;
		}

	}

	/**
	 * @param firstRow
	 *            ,expected[]
	 * @throws Exception
	 * @return {@link Boolean}
	 * */
	private static boolean checkExcelColumnName(Row firstRow, String[] expected)
			throws Exception {
		// TODO Auto-generated method stub
		if (firstRow.getLastCellNum() != expected.length) {
			return false;
		}
		String[] actual = new String[expected.length];
		Iterator<Cell> cell = firstRow.iterator();
		int i = 0;
		while (cell.hasNext()) {
			actual[i] = String.valueOf(cell.next());
			i++;
		}

		return Arrays.equals(expected, actual);
	}
}
