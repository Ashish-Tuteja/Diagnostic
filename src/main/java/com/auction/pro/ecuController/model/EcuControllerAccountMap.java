package com.auction.pro.ecuController.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.auction.pro.common.model.BaseModel;
import com.auction.pro.ecuController.dto.EcuControllerDto;

@Document(collection = "ecuControlleraccountmap")
public class EcuControllerAccountMap extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String ecuControllerId;

	public EcuControllerAccountMap() {
		// TODO Auto-generated constructor stub
	}

	public EcuControllerAccountMap(EcuControllerDto dto) {
		// TODO Auto-generated constructor stub
		this.setEcuControllerId(dto.getId());

	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEcuControllerId() {
		return ecuControllerId;
	}

	public void setEcuControllerId(String ecuControllerId) {
		this.ecuControllerId = ecuControllerId;
	}


}
