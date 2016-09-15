
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
 *         &lt;element name="UnRegisterDeviceResult" type="{https://www.wyless.net/}RegisterResponseType" minOccurs="0"/>
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
    "unRegisterDeviceResult"
})
@XmlRootElement(name = "UnRegisterDeviceResponse")
public class UnRegisterDeviceResponse {

    @XmlElement(name = "UnRegisterDeviceResult")
    protected RegisterResponseType unRegisterDeviceResult;

    /**
     * Gets the value of the unRegisterDeviceResult property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterResponseType }
     *     
     */
    public RegisterResponseType getUnRegisterDeviceResult() {
        return unRegisterDeviceResult;
    }

    /**
     * Sets the value of the unRegisterDeviceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterResponseType }
     *     
     */
    public void setUnRegisterDeviceResult(RegisterResponseType value) {
        this.unRegisterDeviceResult = value;
    }

}
