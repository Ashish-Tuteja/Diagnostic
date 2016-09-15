
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
 *         &lt;element name="BulkActivateCDMAResult" type="{https://www.wyless.net/}BulkResponseType" minOccurs="0"/>
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
    "bulkActivateCDMAResult"
})
@XmlRootElement(name = "BulkActivateCDMAResponse")
public class BulkActivateCDMAResponse {

    @XmlElement(name = "BulkActivateCDMAResult")
    protected BulkResponseType bulkActivateCDMAResult;

    /**
     * Gets the value of the bulkActivateCDMAResult property.
     * 
     * @return
     *     possible object is
     *     {@link BulkResponseType }
     *     
     */
    public BulkResponseType getBulkActivateCDMAResult() {
        return bulkActivateCDMAResult;
    }

    /**
     * Sets the value of the bulkActivateCDMAResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BulkResponseType }
     *     
     */
    public void setBulkActivateCDMAResult(BulkResponseType value) {
        this.bulkActivateCDMAResult = value;
    }

}
