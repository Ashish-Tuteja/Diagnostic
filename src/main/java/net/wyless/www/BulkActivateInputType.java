
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkActivateInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkActivateInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}BulkInputType">
 *       &lt;sequence>
 *         &lt;element name="PricePlanId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SegmentIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Profile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ngp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkActivateInputType", propOrder = {
    "pricePlanId",
    "segmentIP",
    "profile",
    "expirationDate",
    "accountNumber",
    "userName",
    "ngp"
})
public class BulkActivateInputType
    extends BulkInputType
{

    @XmlElement(name = "PricePlanId")
    protected String pricePlanId;
    @XmlElement(name = "SegmentIP")
    protected String segmentIP;
    @XmlElement(name = "Profile")
    protected String profile;
    @XmlElement(name = "ExpirationDate")
    protected String expirationDate;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "Ngp")
    protected String ngp;

    /**
     * Gets the value of the pricePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanId() {
        return pricePlanId;
    }

    /**
     * Sets the value of the pricePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanId(String value) {
        this.pricePlanId = value;
    }

    /**
     * Gets the value of the segmentIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentIP() {
        return segmentIP;
    }

    /**
     * Sets the value of the segmentIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentIP(String value) {
        this.segmentIP = value;
    }

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
     * Gets the value of the ngp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNgp() {
        return ngp;
    }

    /**
     * Sets the value of the ngp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNgp(String value) {
        this.ngp = value;
    }

}
