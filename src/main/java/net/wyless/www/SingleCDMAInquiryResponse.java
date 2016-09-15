
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="SingleCDMAInquiryResult" type="{https://www.wyless.net/}DeviceResponseType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "singleCDMAInquiryResult"
})
@XmlRootElement(name = "SingleCDMAInquiryResponse")
public class SingleCDMAInquiryResponse {

    @XmlElement(name = "SingleCDMAInquiryResult")
    protected DeviceResponseType singleCDMAInquiryResult;

    /**
     * Gets the value of the singleCDMAInquiryResult property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceResponseType }
     *     
     */
    public DeviceResponseType getSingleCDMAInquiryResult() {
        return singleCDMAInquiryResult;
    }

    /**
     * Sets the value of the singleCDMAInquiryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceResponseType }
     *     
     */
    public void setSingleCDMAInquiryResult(DeviceResponseType value) {
        this.singleCDMAInquiryResult = value;
    }

}
