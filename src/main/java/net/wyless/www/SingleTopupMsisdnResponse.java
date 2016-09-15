
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
 *         &lt;element name="SingleTopupMsisdnResult" type="{https://www.wyless.net/}ResponseType" minOccurs="0"/>
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
    "singleTopupMsisdnResult"
})
@XmlRootElement(name = "SingleTopupMsisdnResponse")
public class SingleTopupMsisdnResponse {

    @XmlElement(name = "SingleTopupMsisdnResult")
    protected ResponseType singleTopupMsisdnResult;

    /**
     * Gets the value of the singleTopupMsisdnResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getSingleTopupMsisdnResult() {
        return singleTopupMsisdnResult;
    }

    /**
     * Sets the value of the singleTopupMsisdnResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setSingleTopupMsisdnResult(ResponseType value) {
        this.singleTopupMsisdnResult = value;
    }

}
