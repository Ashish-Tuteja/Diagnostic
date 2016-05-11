package com.auction.pro.vehicle.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.common.utils.CommonUtils;

@Document(collection = "parameter_test")
public class GlobalParameter extends BaseModel {

	private static final long serialVersionUID = 1L;
	private String parameterDesc;
	private String controllerId;
	private String messageType;
	private String isEnhanced;
	private String offset;
	private String bitpostion;
	private String bitwidth;
	private String endness;
	private String serviceId;
	private String parameterId;
	private String encodingType;
	private String isUniqueToECU;
	private String trustFactor;
	private String supportedbyECU;
	private int length;
	private int parameterDescId;

	public GlobalParameter() {
		// TODO Auto-generated constructor stub
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

	public String getTrustFactor() {
		return trustFactor;
	}

	public void setTrustFactor(String trustFactor) {
		this.trustFactor = trustFactor;
	}

	public String getSupportedbyECU() {
		return supportedbyECU;
	}

	public void setSupportedbyECU(String supportedbyECU) {
		this.supportedbyECU = supportedbyECU;
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

	public void setParameterDescId(int parameterDescId) {
		this.parameterDescId = parameterDescId;
	}

	public static GlobalParameter setGlobalparameter(List<String> excelData) {
		// TODO Auto-generated method stub
		GlobalParameter globalParameter = new GlobalParameter();
		globalParameter.setId(CommonUtils.generateUUID());
		globalParameter
				.setParameterDesc(!StringUtils.isEmpty(excelData.get(0)) ? excelData
						.get(0) : "");
		globalParameter
				.setControllerId(!StringUtils.isEmpty(excelData.get(1)) ? excelData
						.get(1) : "");
		globalParameter
				.setMessageType(!StringUtils.isEmpty(excelData.get(2)) ? excelData
						.get(2) : "");
		globalParameter
				.setIsEnhanced(!StringUtils.isEmpty(excelData.get(3)) ? excelData
						.get(3) : "");
		globalParameter
				.setOffset(!StringUtils.isEmpty(excelData.get(4)) ? excelData
						.get(4) : "");
		globalParameter
				.setBitpostion(!StringUtils.isEmpty(excelData.get(5)) ? excelData
						.get(5) : "");
		globalParameter
				.setBitwidth(!StringUtils.isEmpty(excelData.get(6)) ? excelData
						.get(6) : "");
		globalParameter
				.setEndness(!StringUtils.isEmpty(excelData.get(7)) ? excelData
						.get(7) : "");
		globalParameter
				.setServiceId(!StringUtils.isEmpty(excelData.get(8)) ? excelData
						.get(8) : "");
		globalParameter
				.setParameterId(!StringUtils.isEmpty(excelData.get(9)) ? excelData
						.get(9) : "");
		globalParameter
				.setEncodingType(!StringUtils.isEmpty(excelData.get(10)) ? excelData
						.get(10) : "");
		globalParameter
				.setIsUniqueToECU(!StringUtils.isEmpty(excelData.get(11)) ? excelData
						.get(11) : "");
		globalParameter
				.setTrustFactor(!StringUtils.isEmpty(excelData.get(12)) ? excelData
						.get(12) : "");
		globalParameter.setSupportedbyECU(!StringUtils.isEmpty(excelData
				.get(13)) ? excelData.get(13) : "");
		excelData.removeAll(Arrays.asList("", null));
		globalParameter.setLength(excelData.size());
		return globalParameter;

	}

	public static GlobalParameter convertGlobalParameter(JSONObject jsonObject)
			throws JSONException {
		// TODO Auto-generated constructor stub
		GlobalParameter globalParameter = new GlobalParameter();
		globalParameter.setControllerId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("controllerId"))) ? String
				.valueOf(jsonObject.get("controllerId")) : "");

		globalParameter.setSupportedbyECU(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("supportedbyECU"))) ? String
				.valueOf(jsonObject.get("supportedbyECU")) : "");

		globalParameter.setParameterDesc(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("parameterDesc"))) ? String
				.valueOf(jsonObject.get("parameterDesc")) : "");

		globalParameter.setOffset(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("offset"))) ? String.valueOf(jsonObject
				.get("offset")) : "");

		globalParameter.setParameterId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("parameterId"))) ? String
				.valueOf(jsonObject.get("parameterId")) : "");

		globalParameter.setIsUniqueToECU(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("isUniqueToECU"))) ? String
				.valueOf(jsonObject.get("isUniqueToECU")) : "");

		globalParameter.setBitwidth(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("bitwidth"))) ? String
				.valueOf(jsonObject.get("bitwidth")) : "");

		globalParameter.setTrustFactor(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("trustFactor"))) ? String
				.valueOf(jsonObject.get("trustFactor")) : "");

		globalParameter.setEndness(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("endness"))) ? String
				.valueOf(jsonObject.get("endness")) : "");

		globalParameter.setMessageType(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("messageType"))) ? String
				.valueOf(jsonObject.get("messageType")) : "");

		globalParameter.setEncodingType(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("encodingType"))) ? String
				.valueOf(jsonObject.get("encodingType")) : "");

		globalParameter.setServiceId(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("serviceId"))) ? String
				.valueOf(jsonObject.get("serviceId")) : "");

		globalParameter.setIsEnhanced(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("isEnhanced"))) ? String
				.valueOf(jsonObject.get("isEnhanced")) : "");

		globalParameter.setBitpostion(!StringUtils.isEmpty(String
				.valueOf(jsonObject.get("bitpostion"))) ? String
				.valueOf(jsonObject.get("bitpostion")) : "");
		globalParameter
				.setParameterDescId(jsonObject.getInt("parameterDescId"));
		return globalParameter;

	}

}
