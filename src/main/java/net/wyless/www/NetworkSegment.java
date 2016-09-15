
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NetworkSegment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NetworkSegment">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}SearchObject">
 *       &lt;sequence>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Name_AvailableSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="APN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetworkSize" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AvailableSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SegmentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeerToPeer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RequiresUserName" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RequiresPassword" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkSegment", propOrder = {
    "ip",
    "name",
    "nameAvailableSize",
    "apn",
    "operatorName",
    "networkSize",
    "availableSize",
    "segmentType",
    "peerToPeer",
    "requiresUserName",
    "requiresPassword"
})
public class NetworkSegment
    extends SearchObject
{

    @XmlElement(name = "IP")
    protected String ip;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Name_AvailableSize")
    protected String nameAvailableSize;
    @XmlElement(name = "APN")
    protected String apn;
    @XmlElement(name = "OperatorName")
    protected String operatorName;
    @XmlElement(name = "NetworkSize")
    protected long networkSize;
    @XmlElement(name = "AvailableSize")
    protected int availableSize;
    @XmlElement(name = "SegmentType")
    protected String segmentType;
    @XmlElement(name = "PeerToPeer")
    protected boolean peerToPeer;
    @XmlElement(name = "RequiresUserName")
    protected boolean requiresUserName;
    @XmlElement(name = "RequiresPassword")
    protected boolean requiresPassword;

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
     * Gets the value of the nameAvailableSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameAvailableSize() {
        return nameAvailableSize;
    }

    /**
     * Sets the value of the nameAvailableSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameAvailableSize(String value) {
        this.nameAvailableSize = value;
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
     * Gets the value of the operatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Sets the value of the operatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    /**
     * Gets the value of the networkSize property.
     * 
     */
    public long getNetworkSize() {
        return networkSize;
    }

    /**
     * Sets the value of the networkSize property.
     * 
     */
    public void setNetworkSize(long value) {
        this.networkSize = value;
    }

    /**
     * Gets the value of the availableSize property.
     * 
     */
    public int getAvailableSize() {
        return availableSize;
    }

    /**
     * Sets the value of the availableSize property.
     * 
     */
    public void setAvailableSize(int value) {
        this.availableSize = value;
    }

    /**
     * Gets the value of the segmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentType() {
        return segmentType;
    }

    /**
     * Sets the value of the segmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentType(String value) {
        this.segmentType = value;
    }

    /**
     * Gets the value of the peerToPeer property.
     * 
     */
    public boolean isPeerToPeer() {
        return peerToPeer;
    }

    /**
     * Sets the value of the peerToPeer property.
     * 
     */
    public void setPeerToPeer(boolean value) {
        this.peerToPeer = value;
    }

    /**
     * Gets the value of the requiresUserName property.
     * 
     */
    public boolean isRequiresUserName() {
        return requiresUserName;
    }

    /**
     * Sets the value of the requiresUserName property.
     * 
     */
    public void setRequiresUserName(boolean value) {
        this.requiresUserName = value;
    }

    /**
     * Gets the value of the requiresPassword property.
     * 
     */
    public boolean isRequiresPassword() {
        return requiresPassword;
    }

    /**
     * Sets the value of the requiresPassword property.
     * 
     */
    public void setRequiresPassword(boolean value) {
        this.requiresPassword = value;
    }

}
