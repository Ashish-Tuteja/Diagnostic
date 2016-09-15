
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}Device">
 *       &lt;sequence>
 *         &lt;element name="IPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CacheTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationInputType", propOrder = {
    "ipAddress",
    "locationMethod",
    "cacheTime"
})
public class LocationInputType
    extends Device
{

    @XmlElement(name = "IPAddress")
    protected String ipAddress;
    @XmlElement(name = "LocationMethod")
    protected String locationMethod;
    @XmlElement(name = "CacheTime")
    protected String cacheTime;

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
     * Gets the value of the locationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationMethod() {
        return locationMethod;
    }

    /**
     * Sets the value of the locationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationMethod(String value) {
        this.locationMethod = value;
    }

    /**
     * Gets the value of the cacheTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheTime() {
        return cacheTime;
    }

    /**
     * Sets the value of the cacheTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheTime(String value) {
        this.cacheTime = value;
    }

}
