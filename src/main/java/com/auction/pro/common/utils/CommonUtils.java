package com.auction.pro.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auction.pro.common.constants.NavResearchConstants;
import com.google.gson.Gson;

/**
 * @author infoobjects
 *
 */
public class CommonUtils implements NavResearchConstants {
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
	public static Map<Integer, List<String>> readCsv(File file,
			String uploadoption) throws IOException, InvalidFormatException {
		Map<Integer, List<String>> batchcollection = new HashMap<Integer, List<String>>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		List<String> lines = new ArrayList<String>();
		String line = null;
		//		Workbook workbook;
		//		Row row = null;
		int numberofcells = 0;
		//		Sheet sheet = null;
		//		workbook = WorkbookFactory.create(file);
		//		sheet = workbook.getSheetAt(0);
		//		Iterator<Row> rowIterator = sheet.rowIterator();
		// skip first row
		//		row = rowIterator.next();
		boolean isFormat = false;
		boolean checkHeader = false;
		try {
			isFormat = checkFileFormat(file,uploadoption);
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			checkHeader = checkHeader(lines.get(0));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.error("File format not supported ! Upload correct file format as ::: <Make>-<Model>-<Year>-<ControllerName>-<version>.csv");
		}
		
		if(!isFormat){
			System.err.println("You tried to upload :::  "+file.getName()+"  File format not like <Make>-<Model>-<Year>-<ControllerName>-<version>.csv , Change format and try to upload again ");
			return null;
		}
		
		if(!checkHeader){
			System.err.println("Header columns does not match with number of fields being uploaded ! Check dto before uploading ");
		}
		
		
		String[] cells = lines.get(0).split(",");
		numberofcells = cells.length;
		for(int k = 1; k < lines.size(); k++)
		{
//			System.out.println("line: "+lines.get(k));
			String[] columnValue = lines.get(k).split(",",-1);
			//			row = rowIterator.next();
//			System.out.println(columnValue.length +","+numberofcells);
			/*if(columnValue.length == numberofcells)
			{
//				System.out.println("inside column cells");
				for (String cell : columnValue) {
//					System.out.println("cell value:"+ cell);
				}
//				for (int cn = 0; cn <= numberofcells; cn++) {
//					Cell cell = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
//					if (cell == null) {
//						columnValue.add("");
//					} 
//				}
			}
			else {
				System.out.println("cell file size doesnot match with header columns");
				LOGGER.info("Each each cell must contain a valid value or null value in csv file");
			}*/
//			System.out.println(columnValue.toString());
			batchcollection.put(new Integer(k+1),Arrays.asList(columnValue));
		}
		return batchcollection;
	}

	/**
	 * @param firstRow
	 *            ,uploadoption
	 * @throws Exception
	 * @return {@link Boolean}
	 * */
	private static boolean checkFileFormat(File file,String uploadoption)
			throws Exception {
		System.out.println("uploadoption: "+uploadoption);
		String filename = file.getName();
		String[] attributes = filename.split("-{1}|\\_{1}|\\.{1}");
//		for (String fileNameParameter : attributes) {
//			System.out.println("File name parameter  ::" + fileNameParameter);
//		}
		
		if (uploadoption.equalsIgnoreCase(GLOBAL_PARAMETERS_TABLE_NAME)) 
		{
			if(filename.contains(".csv")){
			if(attributes[0] != null && attributes[1] != null && attributes[2] != null && attributes[3] != null)
			{
				System.out.println("You tried to upload:::"+ filename+" ::: File format correct as  <Make>-<Model>-<Year>-<ControllerName>-<version>.csv");
				
				return true;
			}
			}
		}
		return false;

	}


		//		if (uploadoption.equalsIgnoreCase(GLOBAL_PARAMETER_TABLE_NAME)) {
		//			return firstRow.getLastCellNum() == GLOBAL_PARAMETER_TABLE_SIZE
		//					&& checkExcelColumnName(firstRow, GLOBAL_PARAMTER_TABLE) ? true
		//					: false;
		//		} else if (uploadoption.equalsIgnoreCase(VEHICLEECU_TABLE_NAME)) {
		//			return true;
		//		} else if (uploadoption.equalsIgnoreCase(GLOBALECU_TABLE_NAME)) {
		//			return firstRow.getLastCellNum() == GLOBALECU_TABLE_SIZE ? true
		//					: false;
		//		} else if (uploadoption.equalsIgnoreCase(GLOBAL_PARAMETERS_TABLE_NAME)) {
		////			return firstRow.getLastCellNum() == GLOBAL_PARAMETERS_TABLE_SIZE ? true
		////					: false;
		//			return true;
		//		} else {
		//			LOGGER.info("Not a file");
		//			return false;
		//		}

	

	private static boolean checkHeader(String line)
			throws Exception {
		
		String deafultHeader[] = {"Parameter_Index","Parameter_Description","Units","Controller_ID","Message_Type","RX_ID","TX_ID","Extended","Latency","IsEnhanced","Byte_Offset","Bit_Possition","Bit_Width","Endieness","Service_ID","Parameter_ID","0x00 Value","0xFF Value","Encoding_Type","Is_Unique_to_ECU","Supported_By_ECU","WasError","Error_Description"};
		String[] headerFields = line.split(",");
		int i = 0;
		if(headerFields.length == deafultHeader.length)
		{
			for (String header : headerFields) {
				if(header.trim().equalsIgnoreCase(deafultHeader[i]))
				{
					i++;
				}
				else
				{
					return false;
				}
			}
			System.out.println("header matched with Dto");
			return true;
		}
		return false;
	}


	/**
	 * @param firstRow
	 *            ,expected[]
	 * @throws Exception
	 * @return {@link Boolean}
	 * */
	private static boolean checkExcelColumnName(Row firstRow, String[] expected)
			throws Exception {
		System.out.println("last cell no :   " + firstRow.getLastCellNum());
		System.out.println("expected" + expected.length);
		// TODO Auto-generated method stub
		if (firstRow.getLastCellNum() != expected.length) {
			System.out.println("Inside if");
			System.out.println(firstRow.getLastCellNum());
			System.out.println(expected.length);
			return false;
		}
		String[] actual = new String[expected.length];
		String[] actualList = new String[actual.length];
		Iterator<Cell> cell = firstRow.iterator();
		int i = 0;
		while (cell.hasNext()) {
			actual[i] = String.valueOf(cell.next());
			actual[i].trim();
			System.out.println(actual[i].trim());
			i++;
		}
		System.out.println(expected.length);
		System.out.println(Arrays.toString(expected));

		System.out.println(actual.length);
		//		
		System.out.println(Arrays.toString(actual));
		System.out.println(Arrays.equals(expected, actual));
		return Arrays.equals(expected, actual);
	}
}
