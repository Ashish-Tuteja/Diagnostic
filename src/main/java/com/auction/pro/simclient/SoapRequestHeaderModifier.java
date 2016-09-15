package com.auction.pro.simclient;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.springframework.util.Assert;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapElement;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.xml.transform.StringSource;

public class SoapRequestHeaderModifier extends SoapActionCallback{
	private final String soapAction;
	public SoapRequestHeaderModifier(String soapAction) {
		// TODO Auto-generated constructor stub
		super(soapAction);
		this.soapAction = soapAction;
	}
	@Override
	public void doWithMessage(WebServiceMessage message) throws IOException {
		Assert.isInstanceOf(SoapMessage.class, message);
		SoapMessage soapMessage = (SoapMessage) message;
		soapMessage.setSoapAction(soapAction);
		SoapHeader header = ((SoapMessage)message).getSoapHeader();
		StringSource headerSource = new StringSource("<Credential xmlns=\"https://www.wyless.net/\">"+
		      "<Login>"+SimClient.USER+"</Login>"+
		      "<Password>"+SimClient.PASSWORD+"</Password>"+
		      "<SessionKey></SessionKey>"+
		      "<PartnerId>"+SimClient.PARTNER_ID+"</PartnerId>"+
		    "</Credential>");
		try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(headerSource, header.getResult());
		}catch(TransformerException e){
			 e.printStackTrace();
		}

    }
	
	
}