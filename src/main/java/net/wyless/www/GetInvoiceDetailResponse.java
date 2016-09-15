
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
 *         &lt;element name="GetInvoiceDetailResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getInvoiceDetailResult"
})
@XmlRootElement(name = "GetInvoiceDetailResponse")
public class GetInvoiceDetailResponse {

    @XmlElement(name = "GetInvoiceDetailResult")
    protected String getInvoiceDetailResult;

    /**
     * Gets the value of the getInvoiceDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetInvoiceDetailResult() {
        return getInvoiceDetailResult;
    }

    /**
     * Sets the value of the getInvoiceDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetInvoiceDetailResult(String value) {
        this.getInvoiceDetailResult = value;
    }

}
