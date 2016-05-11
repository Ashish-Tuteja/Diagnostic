package com.auction.pro.vehicle.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;

// global ecu 
@Document(collection = "ecu_controller")
public class EcuController extends BaseModel {
	private static final long serialVersionUID = 1L;
	private String controllerId;
	private String rx;
	private String txId;
	private String extendedId;
	private String worstCaseLatency;
	private String broadCastId01;
	private String broadCastId02;

	public EcuController() {
		// TODO Auto-generated constructor stub
	}

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}

	public String getRx() {
		return rx;
	}

	public void setRx(String rx) {
		this.rx = rx;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
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

	public String getBroadCastId01() {
		return broadCastId01;
	}

	public void setBroadCastId01(String broadCastId01) {
		this.broadCastId01 = broadCastId01;
	}

	public String getBroadCastId02() {
		return broadCastId02;
	}

	public void setBroadCastId02(String broadCastId02) {
		this.broadCastId02 = broadCastId02;
	}

	public static EcuController setGlobalECU(List<String> excelData) {
		EcuController globalecu = new EcuController();
		String controllerId = excelData.get(0);
		globalecu
				.setControllerId(!StringUtils.isEmpty(controllerId) ? controllerId
						: "");
		globalecu.setRx(!StringUtils.isEmpty(excelData.get(2)) ? excelData
				.get(2) : "");
		globalecu.setTxId(!StringUtils.isEmpty(excelData.get(3)) ? excelData
				.get(3) : "");
		globalecu
				.setExtendedId(!StringUtils.isEmpty(excelData.get(4)) ? excelData
						.get(4) : "");
		globalecu
				.setWorstCaseLatency(!StringUtils.isEmpty(excelData.get(5)) ? excelData
						.get(5) : "");
		globalecu
				.setBroadCastId01(!StringUtils.isEmpty(excelData.get(6)) ? excelData
						.get(6) : "");
		globalecu
				.setBroadCastId02(!StringUtils.isEmpty(excelData.get(7)) ? excelData
						.get(7) : "");
		return globalecu;
	}
}
