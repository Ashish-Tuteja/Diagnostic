package com.auction.pro.vehicle.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

@Document(collection = "can_parameter_tests")
public class CanParameters extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	
	private String functionId;
	private String messageId;
	private String multiplexMask;
	private String description;
	private String multiplexId;
	private String isLittleEndian;
	private String startingBitNumber;
	private String bitCount;
	private String prescaleOffset;
	private String scaleFactorNumerator;
	private String scaleFactorDenominator;
	private String offset;
	private String manufacturerId;
	private String packetType;
	private String bitPosition;
	
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMultiplexMask() {
		return multiplexMask;
	}
	public void setMultiplexMask(String multiplexMask) {
		this.multiplexMask = multiplexMask;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMultiplexId() {
		return multiplexId;
	}
	public void setMultiplexId(String multiplexId) {
		this.multiplexId = multiplexId;
	}
	public String getIsLittleEndian() {
		return isLittleEndian;
	}
	public void setIsLittleEndian(String isLittleEndian) {
		this.isLittleEndian = isLittleEndian;
	}
	public String getStartingBitNumber() {
		return startingBitNumber;
	}
	public void setStartingBitNumber(String startingBitNumber) {
		this.startingBitNumber = startingBitNumber;
	}
	public String getBitCount() {
		return bitCount;
	}
	public void setBitCount(String bitCount) {
		this.bitCount = bitCount;
	}
	public String getPrescaleOffset() {
		return prescaleOffset;
	}
	public void setPrescaleOffset(String prescaleOffset) {
		this.prescaleOffset = prescaleOffset;
	}
	public String getScaleFactorNumerator() {
		return scaleFactorNumerator;
	}
	public void setScaleFactorNumerator(String scaleFactorNumerator) {
		this.scaleFactorNumerator = scaleFactorNumerator;
	}
	public String getScaleFactorDenominator() {
		return scaleFactorDenominator;
	}
	public void setScaleFactorDenominator(String scaleFactorDenominator) {
		this.scaleFactorDenominator = scaleFactorDenominator;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getPacketType() {
		return packetType;
	}
	public void setPacketType(String packetType) {
		this.packetType = packetType;
	}
	public String getBitPosition() {
		return bitPosition;
	}
	public void setBitPosition(String bitPosition) {
		this.bitPosition = bitPosition;
	}

	

}