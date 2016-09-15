package com.auction.pro.simclient;


import java.rmi.RemoteException;
import java.util.List;

import net.wyless.www.ActivateInputType;
import net.wyless.www.ArrayOfBulkActivateInputType;
import net.wyless.www.BulkActivateInputType;
import net.wyless.www.BulkActivateSIM;
import net.wyless.www.BulkActivateSIMResponse;
import net.wyless.www.Credential;
import net.wyless.www.EchoInt;
import net.wyless.www.EchoIntResponse;
import net.wyless.www.GetNetworkConnectionOnlineStatus;
import net.wyless.www.GetNetworkConnectionOnlineStatusResponse;
import net.wyless.www.InputType;
import net.wyless.www.SingleActivateSIM;
import net.wyless.www.SingleActivateSIMResponse;
import net.wyless.www.SingleCancelDeviceLocation;
import net.wyless.www.SingleCancelDeviceLocationResponse;
import net.wyless.www.SingleQueryHLR;
import net.wyless.www.SingleQueryHLRResponse;
import net.wyless.www.SingleRestoreSIM;
import net.wyless.www.SingleRestoreSIMResponse;
import net.wyless.www.SingleSIMInquiry;
import net.wyless.www.SingleSIMInquiryResponse;
import net.wyless.www.SingleSuspendSIM;
import net.wyless.www.SingleSuspendSIMResponse;
import net.wyless.www.SuspendInputType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class SimClient extends WebServiceGatewaySupport{
	private static final Logger log=LoggerFactory.getLogger(SimClient.class);
	public static final String VERSION 						= "1";
	public static final long   PARTNER_ID 					= 2051;
	public static final String PLAN							= "13021";
	public static final String USER							= "matt.chalker@cimble.com";
	public static final String PASSWORD						= "wyless1533";
	public static final String CARRIER_NAME                 = "WYLESS";
	public static final String HOSTNAME 					= "HOSTNAME";
	public static final String PORT 						= "PORT";
	public static final String ASYNC_URL 					= "ASYNC_URL";
	public static final String NGP 							= "NGP";
	
	public Object getResults(Object request,String action) throws RemoteException{
		Object response = getWebServiceTemplate()
			.marshalSendAndReceive(
						"https://www.wyless.net:9443/PorthosAPI/PorthosWSF2.asmx",
						request,
						new SoapRequestHeaderModifier(action));
		return response;
	}
	
	
	/*Inquire SIM*/
	public SingleSIMInquiryResponse inquireSim(String msisdn,String sim) throws Exception{
		SingleSIMInquiry request = new SingleSIMInquiry();
		InputType inputType=new InputType();
		if (sim != null)
			inputType.setSIMNo(sim);
		
		if (msisdn != null)
			inputType.setMSISDN(msisdn);
        inputType.setTransactionId(""+System.currentTimeMillis());
		
		request.setInputType(inputType);
		
		log.info("Requesting inquiry for msisdn-->  " + inputType.getMSISDN()+" sim --> "+inputType.getSIMNo());
		String action="https://www.wyless.net/SingleSIMInquiry";
		SingleSIMInquiryResponse response=null;
		try{
			response = (SingleSIMInquiryResponse) getResults(request, action);
		}catch(RemoteException ex){
			throw new Exception(ex.getMessage(),ex);
		}
		return response;
	}
	
	/*Echo INT*/
	public EchoIntResponse echoInt(Integer value)throws Exception{
		EchoInt echoIntRequest= new EchoInt();
		echoIntRequest.setValue(value);
		EchoIntResponse echoIntResponse=null;
		log.info("echoing for -->  " + echoIntRequest.getValue());
		String action="https://www.wyless.net/echoInt";
		try{
			echoIntResponse = (EchoIntResponse) getResults(echoIntRequest, action);
		}catch(RemoteException ex){
			throw new Exception(ex.getMessage(),ex);
		}
		
		return echoIntResponse;
	}
	/*Single Activate SIM*/
	public SingleActivateSIMResponse activateSubscriber( String sim) throws Exception{
		SingleActivateSIM singleActivateSIMRequest=new SingleActivateSIM();
		ActivateInputType in=new ActivateInputType();
		in.setSIMNo(sim);
		//--------------Plan value
		in.setPricePlanId(PLAN);
		//--------------Plan value
		
		in.setSegmentIP("10.146.0.0");
		in.setTransactionId(""+System.currentTimeMillis());
		singleActivateSIMRequest.setInputType(in);
		log.info("Requesting to activate for msisdn -->  " + in.getMSISDN()+" sim --> "+in.getSIMNo());
		String action="https://www.wyless.net/SingleActivateSIM";
		SingleActivateSIMResponse response=null;
		try{
			response=(SingleActivateSIMResponse) getResults(singleActivateSIMRequest, action);
		}catch(RemoteException ex){
			throw new Exception(ex.getMessage(),ex);
		}
		return	response;
	}
	/*single Deactivate SIM*/
	public SingleSuspendSIMResponse suspendSubscriber( String msisdn,String sim ) throws Exception{ 
		SingleSuspendSIM request = new SingleSuspendSIM();
		String action="https://www.wyless.net/SingleSuspendSIM";
		SuspendInputType in = new SuspendInputType();
		
		//isReuse For change SIM  
		//in.setReuseForChangeSIM(reuseValue);		
		if (sim != null)
			in.setSIMNo(sim);
		
		if (msisdn != null)
			in.setMSISDN(msisdn);
        in.setTransactionId(""+System.currentTimeMillis());
		request.setInputType(in);
		log.info("Requesting suspension for msisdn-->  " + in.getMSISDN()+" sim --> "+in.getSIMNo());
		SingleSuspendSIMResponse response = null;
		try{
			response = (SingleSuspendSIMResponse) getResults(request, action);
		}catch(RemoteException ex){
			throw new Exception(ex.getMessage(),ex);
		}
		return response;
	}
	/*Bulk activate SIM*/
	/*public BulkActivateSIMResponse bulkActivateSubscriber( List<String>sims, WylessPlan plan ){
		BulkActivateSIM request = new BulkActivateSIM();
		String action="https://www.wyless.net/BulkActivateSIM";
		ArrayOfBulkActivateInputType act = new ArrayOfBulkActivateInputType();
		for (String sim : sims) {
			BulkActivateInputType in = new BulkActivateInputType();
			in.setSIMNo(sim);
			in.setPricePlanId(plan.name());
			
		}
				
		BulkActivateSIMResponse response = null;
		try {
			response = (BulkActivateSIMResponse)getResults(request, action);
		} catch (RemoteException ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	
		return response;
	}*/
	/*Retore Subscriber*/
	public SingleRestoreSIMResponse restoreSubscriber(String msisdn,String sim) throws Exception{
		SingleRestoreSIM request = new SingleRestoreSIM();
		String action="https://www.wyless.net/SingleRestoreSIM";
		InputType in = new InputType();
		if (sim != null)
			in.setSIMNo(sim);
		
		if (msisdn != null)
			in.setMSISDN(msisdn);
        in.setTransactionId(""+System.currentTimeMillis());
		request.setInputType(in);
		log.info("Requesting restorartion for msisdn-->  " + in.getMSISDN()+" sim --> "+in.getSIMNo());
		SingleRestoreSIMResponse response = null;
		try {
			response = (SingleRestoreSIMResponse)getResults(request, action);
		} catch (RemoteException ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	
		return response;
	}
	
	/*GET NETWORK CONNECTION*/
	public GetNetworkConnectionOnlineStatusResponse getNetworkStatus( String msisdn ) throws Exception{
		GetNetworkConnectionOnlineStatus request = new GetNetworkConnectionOnlineStatus();
		String action="https://www.wyless.net/GetNetworkConnectionOnlineStatus";
		request.setMsisdn(msisdn);
		request.setTransactionId(System.currentTimeMillis()+"");
		log.info("Requesting network status for msisdn-->  " + request.getMsisdn());
		GetNetworkConnectionOnlineStatusResponse response = null;
		try {
			response = (GetNetworkConnectionOnlineStatusResponse) getResults(request, action);
		} catch (RemoteException ex) {
			throw new RemoteException(ex.getMessage(), ex);
		}	
		
		return response;
	}
	
	/*Single Device Cancel*/
	public SingleCancelDeviceLocationResponse cancelDeviceLocation( String sim, String msisdn ) throws Exception {
		SingleCancelDeviceLocation request = new SingleCancelDeviceLocation();
		String action="https://www.wyless.net/SingleCancelDeviceLocation";
		InputType in = new InputType();
		
		if (sim != null)
			in.setSIMNo(sim);
		
		if (msisdn != null)
			in.setMSISDN(msisdn);
        
		in.setTransactionId(""+System.currentTimeMillis());
		request.setInputType(in);
		log.info("Requesting location cancellation for msisdn-->  " + in.getMSISDN()+" sim --> "+in.getSIMNo());
		SingleCancelDeviceLocationResponse response = null;
			try {
				response = (SingleCancelDeviceLocationResponse)getResults(request, action);
			} catch (RemoteException ex) {
				throw new Exception(ex.getMessage(), ex);
			}
	
		return response;
	}
	
	/*Single Query HLR*/
	public SingleQueryHLRResponse queryHlr( String sim, String msisdn ) throws Exception {
		SingleQueryHLR request = new SingleQueryHLR();
		String action="https://www.wyless.net/SingleQueryHLR";
		InputType sub = new InputType();
		
		if (sim != null)
			sub.setSIMNo(sim);
		
		if (msisdn != null)
			sub.setMSISDN(msisdn);
		
		sub.setTransactionId(""+System.currentTimeMillis());
		request.setInputType(sub);
		log.info("Requesting Query HLR for msisdn-->  " + sub.getMSISDN()+" sim --> "+sub.getSIMNo());		
		SingleQueryHLRResponse response = null;
			try {
				response = (SingleQueryHLRResponse) getResults(request, action);
			}catch (RemoteException ex) {
				throw new Exception(ex.getMessage(), ex);
			}
		return response;
	}
}
