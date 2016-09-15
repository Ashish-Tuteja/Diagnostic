
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MobileConnection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MobileConnection">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}SearchObject">
 *       &lt;sequence>
 *         &lt;element name="Profile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActiveatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ExpiredOn" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ThresholdLimit" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TrafficData" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="SIMID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlanID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Operator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdminStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnlineStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActivationStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DateActivationStatusChanged" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PoNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Billable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ActiveApn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastSeen" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DataNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FaxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LockStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MobileConnection", propOrder = {
    "profile",
    "activeatedOn",
    "expiredOn",
    "thresholdLimit",
    "trafficData",
    "simid",
    "phone",
    "planID",
    "operator",
    "adminStatus",
    "onlineStatus",
    "accountNumber",
    "userName",
    "activationStatus",
    "dateActivationStatusChanged",
    "ip",
    "poNumber",
    "billable",
    "activeApn",
    "lastSeen",
    "dataNumber",
    "faxNumber",
    "lockStatus"
})
public class MobileConnection
    extends SearchObject
{

    @XmlElement(name = "Profile")
    protected String profile;
    @XmlElement(name = "ActiveatedOn", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeatedOn;
    @XmlElement(name = "ExpiredOn", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiredOn;
    @XmlElement(name = "ThresholdLimit")
    protected float thresholdLimit;
    @XmlElement(name = "TrafficData")
    protected float trafficData;
    @XmlElement(name = "SIMID")
    protected String simid;
    @XmlElement(name = "Phone")
    protected String phone;
    @XmlElement(name = "PlanID")
    protected int planID;
    @XmlElement(name = "Operator")
    protected String operator;
    @XmlElement(name = "AdminStatus")
    protected String adminStatus;
    @XmlElement(name = "OnlineStatus")
    protected String onlineStatus;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "ActivationStatus")
    protected boolean activationStatus;
    @XmlElement(name = "DateActivationStatusChanged", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateActivationStatusChanged;
    @XmlElement(name = "IP")
    protected String ip;
    @XmlElement(name = "PoNumber")
    protected String poNumber;
    @XmlElement(name = "Billable")
    protected boolean billable;
    @XmlElement(name = "ActiveApn")
    protected String activeApn;
    @XmlElement(name = "LastSeen", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSeen;
    @XmlElement(name = "DataNumber")
    protected String dataNumber;
    @XmlElement(name = "FaxNumber")
    protected String faxNumber;
    @XmlElement(name = "LockStatus")
    protected boolean lockStatus;

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * Gets the value of the activeatedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveatedOn() {
        return activeatedOn;
    }

    /**
     * Sets the value of the activeatedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveatedOn(XMLGregorianCalendar value) {
        this.activeatedOn = value;
    }

    /**
     * Gets the value of the expiredOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiredOn() {
        return expiredOn;
    }

    /**
     * Sets the value of the expiredOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiredOn(XMLGregorianCalendar value) {
        this.expiredOn = value;
    }

    /**
     * Gets the value of the thresholdLimit property.
     * 
     */
    public float getThresholdLimit() {
        return thresholdLimit;
    }

    /**
     * Sets the value of the thresholdLimit property.
     * 
     */
    public void setThresholdLimit(float value) {
        this.thresholdLimit = value;
    }

    /**
     * Gets the value of the trafficData property.
     * 
     */
    public float getTrafficData() {
        return trafficData;
    }

    /**
     * Sets the value of the trafficData property.
     * 
     */
    public void setTrafficData(float value) {
        this.trafficData = value;
    }

    /**
     * Gets the value of the simid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIMID() {
        return simid;
    }

    /**
     * Sets the value of the simid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIMID(String value) {
        this.simid = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the planID property.
     * 
     */
    public int getPlanID() {
        return planID;
    }

    /**
     * Sets the value of the planID property.
     * 
     */
    public void setPlanID(int value) {
        this.planID = value;
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

    /**
     * Gets the value of the adminStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminStatus() {
        return adminStatus;
    }

    /**
     * Sets the value of the adminStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminStatus(String value) {
        this.adminStatus = value;
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
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

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
     * Gets the value of the activationStatus property.
     * 
     */
    public boolean isActivationStatus() {
        return activationStatus;
    }

    /**
     * Sets the value of the activationStatus property.
     * 
     */
    public void setActivationStatus(boolean value) {
        this.activationStatus = value;
    }

    /**
     * Gets the value of the dateActivationStatusChanged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateActivationStatusChanged() {
        return dateActivationStatusChanged;
    }

    /**
     * Sets the value of the dateActivationStatusChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateActivationStatusChanged(XMLGregorianCalendar value) {
        this.dateActivationStatusChanged = value;
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
     * Gets the value of the poNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * Sets the value of the poNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoNumber(String value) {
        this.poNumber = value;
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

    /**
     * Gets the value of the activeApn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveApn() {
        return activeApn;
    }

    /**
     * Sets the value of the activeApn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveApn(String value) {
        this.activeApn = value;
    }

    /**
     * Gets the value of the lastSeen property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSeen() {
        return lastSeen;
    }

    /**
     * Sets the value of the lastSeen property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSeen(XMLGregorianCalendar value) {
        this.lastSeen = value;
    }

    /**
     * Gets the value of the dataNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataNumber() {
        return dataNumber;
    }

    /**
     * Sets the value of the dataNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataNumber(String value) {
        this.dataNumber = value;
    }

    /**
     * Gets the value of the faxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets the value of the faxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNumber(String value) {
        this.faxNumber = value;
    }

    /**
     * Gets the value of the lockStatus property.
     * 
     */
    public boolean isLockStatus() {
        return lockStatus;
    }

    /**
     * Sets the value of the lockStatus property.
     * 
     */
    public void setLockStatus(boolean value) {
        this.lockStatus = value;
    }

}
