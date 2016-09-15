
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeviceResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}Response">
 *       &lt;sequence>
 *         &lt;element name="ESNDecimal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ESNHexaDecimal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MEIDHexaDecimal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActivationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceResponseType", propOrder = {
    "esnDecimal",
    "esnHexaDecimal",
    "meidHexaDecimal",
    "activationDate",
    "expirationDate"
})
public class DeviceResponseType
    extends Response
{

    @XmlElement(name = "ESNDecimal")
    protected String esnDecimal;
    @XmlElement(name = "ESNHexaDecimal")
    protected String esnHexaDecimal;
    @XmlElement(name = "MEIDHexaDecimal")
    protected String meidHexaDecimal;
    @XmlElement(name = "ActivationDate")
    protected String activationDate;
    @XmlElement(name = "ExpirationDate")
    protected String expirationDate;

    /**
     * Gets the value of the esnDecimal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESNDecimal() {
        return esnDecimal;
    }

    /**
     * Sets the value of the esnDecimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESNDecimal(String value) {
        this.esnDecimal = value;
    }

    /**
     * Gets the value of the esnHexaDecimal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESNHexaDecimal() {
        return esnHexaDecimal;
    }

    /**
     * Sets the value of the esnHexaDecimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESNHexaDecimal(String value) {
        this.esnHexaDecimal = value;
    }

    /**
     * Gets the value of the meidHexaDecimal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMEIDHexaDecimal() {
        return meidHexaDecimal;
    }

    /**
     * Sets the value of the meidHexaDecimal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMEIDHexaDecimal(String value) {
        this.meidHexaDecimal = value;
    }

    /**
     * Gets the value of the activationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationDate() {
        return activationDate;
    }

    /**
     * Sets the value of the activationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationDate(String value) {
        this.activationDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

}
