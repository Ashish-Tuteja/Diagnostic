
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="BulkSendOTAMSISDNResult" type="{https://www.wyless.net/}BulkResponseType" minOccurs="0"/>
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
    "bulkSendOTAMSISDNResult"
})
@XmlRootElement(name = "BulkSendOTAMSISDNResponse")
public class BulkSendOTAMSISDNResponse {

    @XmlElement(name = "BulkSendOTAMSISDNResult")
    protected BulkResponseType bulkSendOTAMSISDNResult;

    /**
     * Gets the value of the bulkSendOTAMSISDNResult property.
     * 
     * @return
     *     possible object is
     *     {@link BulkResponseType }
     *     
     */
    public BulkResponseType getBulkSendOTAMSISDNResult() {
        return bulkSendOTAMSISDNResult;
    }

    /**
     * Sets the value of the bulkSendOTAMSISDNResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BulkResponseType }
     *     
     */
    public void setBulkSendOTAMSISDNResult(BulkResponseType value) {
        this.bulkSendOTAMSISDNResult = value;
    }

}
