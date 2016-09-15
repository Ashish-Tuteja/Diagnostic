
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PPTPConnection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PPTPConnection">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}SearchObject">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnlineStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastSeen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Billable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PPTPConnection", propOrder = {
    "userName",
    "ip",
    "status",
    "onlineStatus",
    "lastSeen",
    "billable"
})
public class PPTPConnection
    extends SearchObject
{

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "IP")
    protected String ip;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "OnlineStatus")
    protected String onlineStatus;
    @XmlElement(name = "LastSeen")
    protected String lastSeen;
    @XmlElement(name = "Billable")
    protected boolean billable;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIP() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIP(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the onlineStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * Sets the value of the onlineStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineStatus(String value) {
        this.onlineStatus = value;
    }

    /**
     * Gets the value of the lastSeen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSeen() {
        return lastSeen;
    }

    /**
     * Sets the value of the lastSeen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSeen(String value) {
        this.lastSeen = value;
    }

    /**
     * Gets the value of the billable property.
     * 
     */
    public boolean isBillable() {
        return billable;
    }

    /**
     * Sets the value of the billable property.
     * 
     */
    public void setBillable(boolean value) {
        this.billable = value;
    }

}
