
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}DeviceInputOutput">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddressDetails" type="{https://www.wyless.net/}m_AddressDetails"/>
 *         &lt;element name="GeoPosition" type="{https://www.wyless.net/}m_GeoPosition"/>
 *         &lt;element name="IPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationResponseType", propOrder = {
    "result",
    "description",
    "code",
    "time",
    "addressDetails",
    "geoPosition",
    "ipAddress",
    "operator"
})
public class LocationResponseType
    extends DeviceInputOutput
{

    @XmlElement(name = "Result")
    protected String result;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Code")
    protected String code;
    @XmlElement(name = "Time")
    protected String time;
    @XmlElement(name = "AddressDetails", required = true)
    protected MAddressDetails addressDetails;
    @XmlElement(name = "GeoPosition", required = true)
    protected MGeoPosition geoPosition;
    @XmlElement(name = "IPAddress")
    protected String ipAddress;
    @XmlElement(name = "Operator")
    protected String operator;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the addressDetails property.
     * 
     * @return
     *     possible object is
     *     {@link MAddressDetails }
     *     
     */
    public MAddressDetails getAddressDetails() {
        return addressDetails;
    }

    /**
     * Sets the value of the addressDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link MAddressDetails }
     *     
     */
    public void setAddressDetails(MAddressDetails value) {
        this.addressDetails = value;
    }

    /**
     * Gets the value of the geoPosition property.
     * 
     * @return
     *     possible object is
     *     {@link MGeoPosition }
     *     
     */
    public MGeoPosition getGeoPosition() {
        return geoPosition;
    }

    /**
     * Sets the value of the geoPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link MGeoPosition }
     *     
     */
    public void setGeoPosition(MGeoPosition value) {
        this.geoPosition = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperator(String value) {
        this.operator = value;
    }

}
