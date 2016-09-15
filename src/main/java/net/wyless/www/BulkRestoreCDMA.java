
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
 *         &lt;element name="bulkResInputType" type="{https://www.wyless.net/}ArrayOfBulkDeviceInputType" minOccurs="0"/>
 *         &lt;element name="transactionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callBackURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "bulkResInputType",
    "transactionid",
    "callBackURL"
})
@XmlRootElement(name = "BulkRestoreCDMA")
public class BulkRestoreCDMA {

    protected ArrayOfBulkDeviceInputType bulkResInputType;
    protected String transactionid;
    protected String callBackURL;

    /**
     * Gets the value of the bulkResInputType property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBulkDeviceInputType }
     *     
     */
    public ArrayOfBulkDeviceInputType getBulkResInputType() {
        return bulkResInputType;
    }

    /**
     * Sets the value of the bulkResInputType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBulkDeviceInputType }
     *     
     */
    public void setBulkResInputType(ArrayOfBulkDeviceInputType value) {
        this.bulkResInputType = value;
    }

    /**
     * Gets the value of the transactionid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionid() {
        return transactionid;
    }

    /**
     * Sets the value of the transactionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionid(String value) {
        this.transactionid = value;
    }

    /**
     * Gets the value of the callBackURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallBackURL() {
        return callBackURL;
    }

    /**
     * Sets the value of the callBackURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallBackURL(String value) {
        this.callBackURL = value;
    }

}
