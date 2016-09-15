
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
 *         &lt;element name="BulkRestoreSIMResult" type="{https://www.wyless.net/}BulkResponseType" minOccurs="0"/>
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
    "bulkRestoreSIMResult"
})
@XmlRootElement(name = "BulkRestoreSIMResponse")
public class BulkRestoreSIMResponse {

    @XmlElement(name = "BulkRestoreSIMResult")
    protected BulkResponseType bulkRestoreSIMResult;

    /**
     * Gets the value of the bulkRestoreSIMResult property.
     * 
     * @return
     *     possible object is
     *     {@link BulkResponseType }
     *     
     */
    public BulkResponseType getBulkRestoreSIMResult() {
        return bulkRestoreSIMResult;
    }

    /**
     * Sets the value of the bulkRestoreSIMResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BulkResponseType }
     *     
     */
    public void setBulkRestoreSIMResult(BulkResponseType value) {
        this.bulkRestoreSIMResult = value;
    }

}
