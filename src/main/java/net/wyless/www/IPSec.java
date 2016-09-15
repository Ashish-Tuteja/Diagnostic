
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IPSec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IPSec">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}SearchObject">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeerIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="APN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ISAKMPKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KeyExchangeMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPSec", propOrder = {
    "name",
    "peerIP",
    "apn",
    "isakmpKey",
    "keyExchangeMode"
})
public class IPSec
    extends SearchObject
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "PeerIP")
    protected String peerIP;
    @XmlElement(name = "APN")
    protected String apn;
    @XmlElement(name = "ISAKMPKey")
    protected String isakmpKey;
    @XmlElement(name = "KeyExchangeMode")
    protected String keyExchangeMode;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the peerIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeerIP() {
        return peerIP;
    }

    /**
     * Sets the value of the peerIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeerIP(String value) {
        this.peerIP = value;
    }

    /**
     * Gets the value of the apn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPN() {
        return apn;
    }

    /**
     * Sets the value of the apn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPN(String value) {
        this.apn = value;
    }

    /**
     * Gets the value of the isakmpKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISAKMPKey() {
        return isakmpKey;
    }

    /**
     * Sets the value of the isakmpKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISAKMPKey(String value) {
        this.isakmpKey = value;
    }

    /**
     * Gets the value of the keyExchangeMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyExchangeMode() {
        return keyExchangeMode;
    }

    /**
     * Sets the value of the keyExchangeMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyExchangeMode(String value) {
        this.keyExchangeMode = value;
    }

}
