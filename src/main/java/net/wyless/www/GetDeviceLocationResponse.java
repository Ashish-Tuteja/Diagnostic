
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
 *         &lt;element name="GetDeviceLocationResult" type="{https://www.wyless.net/}LocationResponseType" minOccurs="0"/>
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
    "getDeviceLocationResult"
})
@XmlRootElement(name = "GetDeviceLocationResponse")
public class GetDeviceLocationResponse {

    @XmlElement(name = "GetDeviceLocationResult")
    protected LocationResponseType getDeviceLocationResult;

    /**
     * Gets the value of the getDeviceLocationResult property.
     * 
     * @return
     *     possible object is
     *     {@link LocationResponseType }
     *     
     */
    public LocationResponseType getGetDeviceLocationResult() {
        return getDeviceLocationResult;
    }

    /**
     * Sets the value of the getDeviceLocationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationResponseType }
     *     
     */
    public void setGetDeviceLocationResult(LocationResponseType value) {
        this.getDeviceLocationResult = value;
    }

}
