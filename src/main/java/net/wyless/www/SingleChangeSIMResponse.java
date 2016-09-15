
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
 *         &lt;element name="SingleChangeSIMResult" type="{https://www.wyless.net/}ChangeSIMResponseType" minOccurs="0"/>
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
    "singleChangeSIMResult"
})
@XmlRootElement(name = "SingleChangeSIMResponse")
public class SingleChangeSIMResponse {

    @XmlElement(name = "SingleChangeSIMResult")
    protected ChangeSIMResponseType singleChangeSIMResult;

    /**
     * Gets the value of the singleChangeSIMResult property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeSIMResponseType }
     *     
     */
    public ChangeSIMResponseType getSingleChangeSIMResult() {
        return singleChangeSIMResult;
    }

    /**
     * Sets the value of the singleChangeSIMResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeSIMResponseType }
     *     
     */
    public void setSingleChangeSIMResult(ChangeSIMResponseType value) {
        this.singleChangeSIMResult = value;
    }

}
