
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
 *         &lt;element name="SingleQueryHLRResult" type="{https://www.wyless.net/}QueryHLRResponseType" minOccurs="0"/>
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
    "singleQueryHLRResult"
})
@XmlRootElement(name = "SingleQueryHLRResponse")
public class SingleQueryHLRResponse {

    @XmlElement(name = "SingleQueryHLRResult")
    protected QueryHLRResponseType singleQueryHLRResult;

    /**
     * Gets the value of the singleQueryHLRResult property.
     * 
     * @return
     *     possible object is
     *     {@link QueryHLRResponseType }
     *     
     */
    public QueryHLRResponseType getSingleQueryHLRResult() {
        return singleQueryHLRResult;
    }

    /**
     * Sets the value of the singleQueryHLRResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryHLRResponseType }
     *     
     */
    public void setSingleQueryHLRResult(QueryHLRResponseType value) {
        this.singleQueryHLRResult = value;
    }

}
