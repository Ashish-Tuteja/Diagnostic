
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
 *         &lt;element name="BulkReActivateSIMResult" type="{https://www.wyless.net/}BulkResponseType" minOccurs="0"/>
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
    "bulkReActivateSIMResult"
})
@XmlRootElement(name = "BulkReActivateSIMResponse")
public class BulkReActivateSIMResponse {

    @XmlElement(name = "BulkReActivateSIMResult")
    protected BulkResponseType bulkReActivateSIMResult;

    /**
     * Gets the value of the bulkReActivateSIMResult property.
     * 
     * @return
     *     possible object is
     *     {@link BulkResponseType }
     *     
     */
    public BulkResponseType getBulkReActivateSIMResult() {
        return bulkReActivateSIMResult;
    }

    /**
     * Sets the value of the bulkReActivateSIMResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BulkResponseType }
     *     
     */
    public void setBulkReActivateSIMResult(BulkResponseType value) {
        this.bulkReActivateSIMResult = value;
    }

}
