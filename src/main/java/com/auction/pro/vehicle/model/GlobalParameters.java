package com.auction.pro.vehicle.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

@Document(collection = "parameter_tests")
public class GlobalParameters extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String parameterIndex;
	private String parameterDesc;
	private String units;
	private String controllerId;
	private String messageType;
	private String rxId;
	private String txId;
	private String extendedId;
	private String worstCaseLatency;
	private String isEnhanced;
	private String offset;
	private String bitpostion;
	private String bitwidth;
	private String endness;
	private String serviceId;
	private String parameterId;
	private String ValueOOx;
	private String ValueOFF;
	private String encodingType;
	private String isUniqueToECU;
	private String supportedByECU;
	private String wasError;
	private String errorDesc;
	private int length;
	private int parameterDescId; //Unique for each parameter
	private static int parameterSequence=1;
	// Add VehicleControllerID
	//private String vehicleControllerId;

	public void setParameterDescId(int parameterDescId) {
		this.parameterDescId = parameterDescId;
	}

	public String getValueOOx() {
		return ValueOOx;
	}

	public void setValueOOx(String valueOOx) {
		ValueOOx = valueOOx;
	}

	public String getValueOFF() {
		return ValueOFF;
	}

	public void setValueOFF(String valueOFF) {
		ValueOFF = valueOFF;
	}

	public String getExtendedId() {
		return extendedId;
	}

	public void setExtendedId(String extendedId) {
		this.extendedId = extendedId;
	}

	public String getWorstCaseLatency() {
		return worstCaseLatency;
	}

	public void setWorstCaseLatency(String worstCaseLatency) {
		this.worstCaseLatency = worstCaseLatency;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getParameterIndex() {
		return parameterIndex;
	}

	public void setParameterIndex(String parameterIndex) {
		this.parameterIndex = parameterIndex;
	}

	public String getRxId() {
		return rxId;
	}

	public void setRxId(String rxId) {
		this.rxId = rxId;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getSupportedByECU() {
		return supportedByECU;
	}

	public void setSupportedByECU(String supportedByECU) {
		this.supportedByECU = supportedByECU;
	}

	public String getWasError() {
		return wasError;
	}

	public void setWasError(String wasError) {
		this.wasError = wasError;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public GlobalParameters() {
		// TODO Auto-generated constructor stub
	}
	
	public GlobalParameters(GlobalParameters obj) {
		

		setParameterIndex(obj.getParameterIndex());
		setParameterDesc(obj.getParameterDesc());
		setUnits(obj.getUnits());
		setControllerId(obj.getControllerId());
		setMessageType(obj.getMessageType());
		setRxId(obj.getRxId());
		setTxId(obj.getTxId());
		setExtendedId(getExtendedId());
		setWorstCaseLatency(getWorstCaseLatency());
		setIsEnhanced(getIsEnhanced());
		setOffset(obj.getOffset());
		setBitpostion(obj.getBitpostion());
		setBitwidth(obj.getBitwidth());
		setEndness(obj.getEndness());
		setServiceId(obj.getServiceId());
		setParameterId(obj.getParameterId());
		setValueOOx(obj.getValueOFF());
		setValueOFF(obj.getValueOFF());
		setEncodingType(obj.getEncodingType());
		setIsUniqueToECU(obj.getIsUniqueToECU());
		setSupportedByECU(obj.getSupportedByECU());
		setWasError(obj.getWasError());
		setErrorDesc(obj.getErrorDesc());
		setLength(obj.getLength());
		setParameterDescId(obj.getParameterDescId()); //Unique for each parameter
	}

	public String getParameterDesc() {
		return parameterDesc;
	}

	public void setParameterDesc(String parameterDesc) {
		this.parameterDesc = parameterDesc;
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getIsEnhanced() {
		return isEnhanced;
	}

	public void setIsEnhanced(String isEnhanced) {
		this.isEnhanced = isEnhanced;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getBitpostion() {
		return bitpostion;
	}

	public void setBitpostion(String bitpostion) {
		this.bitpostion = bitpostion;
	}

	public String getBitwidth() {
		return bitwidth;
	}

	public void setBitwidth(String bitwidth) {
		this.bitwidth = bitwidth;
	}

	public String getEndness() {
		return endness;
	}

	public void setEndness(String endness) {
		this.endness = endness;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	public String getIsUniqueToECU() {
		return isUniqueToECU;
	}

	public void setIsUniqueToECU(String isUniqueToECU) {
		this.isUniqueToECU = isUniqueToECU;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getParameterDescId() {
		return parameterDescId;
	}

	

	public static GlobalParameters setGlobalparameters(List<String> csvData) {
		// TODO Auto-generated method stub
		GlobalParameters globalParameters = new GlobalParameters();
		//globalParameters.setId(CommonUtils.generateUUID());

		globalParameters.setParameterIndex(!StringUtils.isEmpty(csvData
				.get(0)) ? csvData.get(0) : "");
		globalParameters
				.setParameterDesc(!StringUtils.isEmpty(csvData.get(1)) ? csvData
						.get(1) : "");
		globalParameters
				.setUnits(!StringUtils.isEmpty(csvData.get(2)) ? csvData
						.get(2) : "");
		globalParameters
				.setControllerId(!StringUtils.isEmpty(csvData.get(3)) ? csvData
						.get(3) : "");
		globalParameters
				.setMessageType(!StringUtils.isEmpty(csvData.get(4)) ? csvData
						.get(4) : "");
		globalParameters
				.setRxId(!StringUtils.isEmpty(csvData.get(5)) ? csvData
						.get(5) : "0x7E8");
		globalParameters
				.setTxId(!StringUtils.isEmpty(csvData.get(6)) ? csvData
						.get(6) : "0x7E0");
		globalParameters
				.setExtendedId(!StringUtils.isEmpty(csvData.get(7)) ? csvData
						.get(7) : "");
		globalParameters.setWorstCaseLatency(!StringUtils.isEmpty(csvData
				.get(8)) ? csvData.get(8) : "");
		globalParameters
				.setIsEnhanced(!StringUtils.isEmpty(csvData.get(9)) ? csvData
						.get(9) : "");
		globalParameters
				.setOffset(!StringUtils.isEmpty(csvData.get(10)) ? csvData
						.get(10) : "");
		globalParameters
				.setBitpostion(!StringUtils.isEmpty(csvData.get(11)) ? csvData
						.get(11) : "");
		globalParameters
				.setBitwidth(!StringUtils.isEmpty(csvData.get(12)) ? csvData
						.get(12) : "");

		if (csvData.get(13).contains("Big")) {
			globalParameters.setEndness("0");
		} else {
			globalParameters.setEndness("1");
		}

//		globalParameters
//				.setEndness(!StringUtils.isEmpty(csvData.get(13)) ? csvData
//						.get(13) : "");
		globalParameters
				.setServiceId(!StringUtils.isEmpty(csvData.get(14)) ? csvData
						.get(14) : "");
		globalParameters
				.setParameterId(!StringUtils.isEmpty(csvData.get(15)) ? csvData
						.get(15) : "");
		globalParameters
				.setValueOOx(!StringUtils.isEmpty(csvData.get(16)) ? csvData
						.get(16) : "");
		globalParameters
				.setValueOFF(!StringUtils.isEmpty(csvData.get(17)) ? csvData
						.get(17) : "");
		globalParameters
				.setEncodingType(!StringUtils.isEmpty(csvData.get(18)) ? csvData
						.get(18) : "");

		globalParameters.setIsUniqueToECU((csvData.size() > 19 && !StringUtils.isEmpty(csvData
				.get(19))) ? csvData.get(19) : "");

		globalParameters.setSupportedByECU(csvData.size() > 20 && !StringUtils.isEmpty(csvData
				.get(20)) ? csvData.get(20) : "");

		globalParameters
				.setWasError(csvData.size() > 21 && !StringUtils.isEmpty(csvData.get(21)) ? csvData
						.get(21) : "");

		globalParameters
				.setErrorDesc(csvData.size() > 22 && !StringUtils.isEmpty(csvData.get(22)) ? csvData
						.get(22) : "");

//		globalParameters.setVehicleControllerId(vehicleControllerID);
		globalParameters.setParameterDescId(parameterSequence);
		parameterSequence ++;

//		csvData.removeAll(Arrays.asList("", null));
//		System.out.println(csvData.size());
//		globalParameters.setLength(csvData.size() + 1);

		return globalParameters;

	}

	
	public static void clearSequence(){
		
		parameterSequence=1;
	}
	
	public static GlobalParameters convertGlobalParameters(JSONObject jsonObject)
			throws JSONException {
		// TODO Auto-generated constructor stub
		GlobalParameters globalParameters = new GlobalParameters();

		globalParameters.setParameterIndex(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("parameterIndex"))) ? String
				.valueOf(jsonObject.get("parameterIndex")) : "");

		globalParameters.setParameterDesc(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("parameterDesc"))) ? String
				.valueOf(jsonObject.get("parameterDesc")) : "");

		globalParameters.setUnits(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("units"))) ? String.valueOf(jsonObject
				.get("units")) : "");

		globalParameters.setControllerId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("controllerId"))) ? String
				.valueOf(jsonObject.get("controllerId")) : "");

		globalParameters.setMessageType(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("messageType"))) ? String
				.valueOf(jsonObject.get("messageType")) : "");

		globalParameters.setRxId(!StringUtils.isEmpty(String.valueOf(jsonObject
				.get("rxId"))) ? String.valueOf(jsonObject.get("rxId")) : "");

		globalParameters.setTxId(!StringUtils.isEmpty(String.valueOf(jsonObject
				.get("txId"))) ? String.valueOf(jsonObject.get("txId")) : "");

		globalParameters.setExtendedId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("extendedId"))) ? String
				.valueOf(jsonObject.get("extendedId")) : "");

		globalParameters.setWorstCaseLatency(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("worstCaseLatency"))) ? String
				.valueOf(jsonObject.get("worstCaseLatency")) : "");

		globalParameters.setIsEnhanced(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("isEnhanced"))) ? String
				.valueOf(jsonObject.get("isEnhanced")) : "");

		globalParameters.setOffset(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("offset"))) ? String.valueOf(jsonObject
				.get("offset")) : "");

		globalParameters.setBitpostion(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("bitpostion"))) ? String
				.valueOf(jsonObject.get("bitpostion")) : "");

		globalParameters.setBitwidth(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("bitwidth"))) ? String
				.valueOf(jsonObject.get("bitwidth")) : "");

		globalParameters.setEndness(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("endness"))) ? String
				.valueOf(jsonObject.get("endness")) : "");

		globalParameters.setServiceId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("serviceId"))) ? String
				.valueOf(jsonObject.get("serviceId")) : "");

		globalParameters.setParameterId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("parameterId"))) ? String
				.valueOf(jsonObject.get("parameterId")) : "");

		/*
		 * globalParameters.setValueOOx(!StringUtils.isEmpty(String
		 * .valueOf(jsonObject.get("ValueOOx"))) ? String
		 * .valueOf(jsonObject.get("ValueOOx")) : "");
		 * 
		 * globalParameters.setValueOFF(!StringUtils.isEmpty(String
		 * .valueOf(jsonObject.get("ValueOFF"))) ? String
		 * .valueOf(jsonObject.get("ValueOFF")) : "");
		 */

		globalParameters.setEncodingType(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("encodingType"))) ? String
				.valueOf(jsonObject.get("encodingType")) : "");

		globalParameters.setIsUniqueToECU(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("isUniqueToECU"))) ? String
				.valueOf(jsonObject.get("isUniqueToECU")) : "");

		globalParameters.setSupportedByECU(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("supportedByECU"))) ? String
				.valueOf(jsonObject.get("supportedByECU")) : "");

		globalParameters.setWasError(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("wasError"))) ? String
				.valueOf(jsonObject.get("wasError")) : "");

		globalParameters.setErrorDesc(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("errorDesc"))) ? String
				.valueOf(jsonObject.get("errorDesc")) : "");

	    globalParameters.setParameterDescId(jsonObject
				.getInt("parameterDescId"));
		return globalParameters;

	}

}