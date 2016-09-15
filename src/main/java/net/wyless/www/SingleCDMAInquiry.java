
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
 *         &lt;element name="inputType" type="{https://www.wyless.net/}DeviceInputType" minOccurs="0"/>
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
    "inputType"
})
@XmlRootElement(name = "SingleCDMAInquiry")
public class SingleCDMAInquiry {

    protected DeviceInputType inputType;

    /**
     * Gets the value of the inputType property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceInputType }
     *     
     */
    public DeviceInputType getInputType() {
        return inputType;
    }

    /**
     * Sets the value of the inputType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceInputType }
     *     
     */
    public void setInputType(DeviceInputType value) {
        this.inputType = value;
    }

}
