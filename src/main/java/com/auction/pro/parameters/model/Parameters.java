package com.auction.pro.parameters.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.parameters.dto.ParametersDto;
@Document(collection = "parameter_tests")
public class Parameters extends BaseModel {

	/**
	 * 	Model class of Parameters
	 */
	private static final long serialVersionUID = 1L;
	private List<String> parentAccountId;
	private String txId;
	private String valueOFF;
	private String parameterId;
	private String endness;
	private String isEnhanced;
	private String encodingType;
	private String length;
	private String parameterDescId;
	private String offset;
	private String wasError;
	private String controllerId;
	private String worstCaseLatency;
	private String errorDesc;
	private String bitpostion;
	private String valueOOx;
	private String messageType;
	private String supportedByECU;
	private String bitwidth;
	private String units;
	private String extendedId;
	private String rxId;
	private String serviceId;
	private String parameterDesc;
	private String parameterIndex;
	private String isUniqueToECU;
	private String formula;

	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public Parameters() {
		// TODO Auto-generated constructor stub
	}

	public Parameters(ParametersDto parametersDto) {
		// TODO Auto-generated constructor stub
		super(parametersDto);
		this.txId = !StringUtils.isEmpty(parametersDto.getTxId()) ? parametersDto.getTxId(): "";
		this.valueOFF = !StringUtils.isEmpty(parametersDto.getValueOFF()) ? parametersDto.getValueOFF() : "";
		this.parameterId = !StringUtils.isEmpty(parametersDto.getParameterId()) ? parametersDto.getParameterId() : "";
		this.endness = !StringUtils.isEmpty(parametersDto.getEndness()) ? parametersDto.getEndness() : "";
		this.isEnhanced = !StringUtils.isEmpty(parametersDto.getIsEnhanced()) ? parametersDto.getIsEnhanced() : "";
		this.encodingType = !StringUtils.isEmpty(parametersDto.getEncodingType()) ? parametersDto.getEncodingType() : "";
		this.length = !StringUtils.isEmpty(parametersDto.getLength()) ? parametersDto.getLength() : "";
		this.parameterDescId= !StringUtils.isEmpty(parametersDto.getParameterDescId()) ? parametersDto.getParameterDescId() : "";
		this.offset = !StringUtils.isEmpty(parametersDto.getOffset()) ? parametersDto.getOffset() : "";
		this.wasError = !StringUtils.isEmpty(parametersDto.getWasError()) ? parametersDto.getWasError() : "";
		this.controllerId = !StringUtils.isEmpty(parametersDto.getControllerId()) ? parametersDto.getControllerId() : "";
		this.worstCaseLatency = !StringUtils.isEmpty(parametersDto.getWorstCaseLatency()) ? parametersDto.getWorstCaseLatency() : "";
		this.errorDesc = !StringUtils.isEmpty(parametersDto.getErrorDesc()) ? parametersDto.getErrorDesc() : "";
		this.bitpostion = !StringUtils.isEmpty(parametersDto.getBitpostion()) ? parametersDto.getBitpostion() : "";
		this.valueOOx = !StringUtils.isEmpty(parametersDto.getValueOOx()) ? parametersDto.getValueOOx() : "";
		this.messageType = !StringUtils.isEmpty(parametersDto.getMessageType()) ? parametersDto.getMessageType() : "";
		this.supportedByECU = !StringUtils.isEmpty(parametersDto.getSupportedByECU()) ? parametersDto.getSupportedByECU() : "";
		this.bitwidth = !StringUtils.isEmpty(parametersDto.getBitwidth()) ? parametersDto.getBitwidth() : "";
		this.units = !StringUtils.isEmpty(parametersDto.getUnits()) ? parametersDto.getUnits() : "";
		this.extendedId = !StringUtils.isEmpty(parametersDto.getExtendedId()) ? parametersDto.getExtendedId() : "";
		this.rxId = !StringUtils.isEmpty(parametersDto.getRxId()) ? parametersDto.getRxId() : "";
		this.serviceId = !StringUtils.isEmpty(parametersDto.getServiceId()) ? parametersDto.getServiceId() : "";
		this.parameterDesc = !StringUtils.isEmpty(parametersDto.getParameterDesc()) ? parametersDto.getParameterDesc() : "";
		this.parameterIndex = !StringUtils.isEmpty(parametersDto.getParameterIndex()) ? parametersDto.getParameterIndex() : "";
		this.isUniqueToECU = !StringUtils.isEmpty(parametersDto.getIsUniqueToECU()) ? parametersDto.getIsUniqueToECU() : "";
		this.formula = !StringUtils.isEmpty(parametersDto.getFormula()) ? parametersDto.getFormula() : "";
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getValueOFF() {
		return valueOFF;
	}

	public void setValueOFF(String valueOFF) {
		this.valueOFF = valueOFF;
	}

	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getEndness() {
		return endness;
	}

	public void setEndness(String endness) {
		this.endness = endness;
	}

	public String getIsEnhanced() {
		return isEnhanced;
	}

	public void setIsEnhanced(String isEnhanced) {
		this.isEnhanced = isEnhanced;
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getParameterDescId() {
		return parameterDescId;
	}

	public void setParameterDescId(String parameterDescId) {
		this.parameterDescId = parameterDescId;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getWasError() {
		return wasError;
	}

	public void setWasError(String wasError) {
		this.wasError = wasError;
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public String getWorstCaseLatency() {
		return worstCaseLatency;
	}

	public void setWorstCaseLatency(String worstCaseLatency) {
		this.worstCaseLatency = worstCaseLatency;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getBitpostion() {
		return bitpostion;
	}

	public void setBitpostion(String bitpostion) {
		this.bitpostion = bitpostion;
	}

	public String getValueOOx() {
		return valueOOx;
	}

	public void setValueOOx(String valueOOx) {
		this.valueOOx = valueOOx;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSupportedByECU() {
		return supportedByECU;
	}

	public void setSupportedByECU(String supportedByECU) {
		this.supportedByECU = supportedByECU;
	}

	public String getBitwidth() {
		return bitwidth;
	}

	public void setBitwidth(String bitwidth) {
		this.bitwidth = bitwidth;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getExtendedId() {
		return extendedId;
	}

	public void setExtendedId(String extendedId) {
		this.extendedId = extendedId;
	}

	public String getRxId() {
		return rxId;
	}

	public void setRxId(String rxId) {
		this.rxId = rxId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getParameterDesc() {
		return parameterDesc;
	}

	public void setParameterDesc(String parameterDesc) {
		this.parameterDesc = parameterDesc;
	}

	public String getParameterIndex() {
		return parameterIndex;
	}

	public void setParameterIndex(String parameterIndex) {
		this.parameterIndex = parameterIndex;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getIsUniqueToECU() {
		return isUniqueToECU;
	}

	public void setIsUniqueToECU(String isUniqueToECU) {
		this.isUniqueToECU = isUniqueToECU;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
