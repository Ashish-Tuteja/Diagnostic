
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputType" type="{https://www.wyless.net/}InputType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "SingleSimInquiryWithCredentials")
public class SingleSimInquiryWithCredentials extends Credential{
	protected SingleSIMInquiry singleSIMInquiry;
	public SingleSimInquiryWithCredentials() {
		// TODO Auto-generated constructor stub
	}
	
	
	public SingleSimInquiryWithCredentials(SingleSIMInquiry inquiry,Credential credential) {
		// TODO Auto-generated constructor stub
		setLogin(credential.getLogin());
		setPartnerId(credential.getPartnerId());
		setPassword(credential.getPassword());
		setSessionKey(credential.getSessionKey());
		setSingleSimInquiry(inquiry);
	}
	
	public void setSingleSimInquiry(SingleSIMInquiry singleSIMInquiry){
		this.singleSIMInquiry=singleSIMInquiry;
	}
	
	public SingleSIMInquiry getSingleSimInquiry(){
		return this.singleSIMInquiry;
	}
}
