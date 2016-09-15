
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
 *         &lt;element name="BulkChangeSIMResult" type="{https://www.wyless.net/}BulkResponseType" minOccurs="0"/>
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
    "bulkChangeSIMResult"
})
@XmlRootElement(name = "BulkChangeSIMResponse")
public class BulkChangeSIMResponse {

    @XmlElement(name = "BulkChangeSIMResult")
    protected BulkResponseType bulkChangeSIMResult;

    /**
     * Gets the value of the bulkChangeSIMResult property.
     * 
     * @return
     *     possible object is
     *     {@link BulkResponseType }
     *     
     */
    public BulkResponseType getBulkChangeSIMResult() {
        return bulkChangeSIMResult;
    }

    /**
     * Sets the value of the bulkChangeSIMResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BulkResponseType }
     *     
     */
    public void setBulkChangeSIMResult(BulkResponseType value) {
        this.bulkChangeSIMResult = value;
    }

}
