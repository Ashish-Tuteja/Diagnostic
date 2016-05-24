package com.auction.pro.parameters.dto;

import java.util.List;

import com.auction.pro.common.dto.BaseDTO;
import com.auction.pro.parameters.model.Parameters;

public class ParametersDto extends BaseDTO {

	
	
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

	public ParametersDto() {
		// TODO Auto-generated constructor stub
	}

	public ParametersDto(Parameters entity) {
		
		// TODO Auto-generated constructor stub
		if (entity != null) {
			setId(entity.getId());
			this.parentAccountId = entity.getParentAccountId();
			this.txId = entity.getTxId();
			this.valueOFF = entity.getValueOFF();
			this.parameterId = entity.getParameterId();
			this.endness = entity.getEndness();
			this.isEnhanced = entity.getIsEnhanced();
			this.encodingType = entity.getEncodingType();
			this.length = entity.getLength();
			this.parameterDescId = entity.getParameterDescId();
			this.offset = entity.getOffset();
			this.wasError = entity.getWasError();
			this.controllerId = entity.getControllerId();
			this.worstCaseLatency = entity.getWorstCaseLatency();
			this.errorDesc = entity.getErrorDesc();
			this.bitpostion = entity.getBitpostion();
			this.valueOOx = entity.getValueOOx();
			this.messageType = entity.getMessageType();
			this.supportedByECU = entity.getSupportedByECU();
			this.bitwidth = entity.getBitwidth();
			this.units = entity.getUnits();
			this.extendedId = entity.getExtendedId();
			this.rxId = entity.getRxId();
			this.serviceId = entity.getServiceId();
			this.parameterDesc = entity.getParameterDesc();
			this.parameterIndex = entity.getParameterIndex();
			this.isUniqueToECU = entity.getIsUniqueToECU();
			
			
			
		}
	}


	public List<String> getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(List<String> parentAccountId) {
		this.parentAccountId = parentAccountId;
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
