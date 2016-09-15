
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
 *         &lt;element name="SingleDeActivateSIMResult" type="{https://www.wyless.net/}ResponseType" minOccurs="0"/>
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
    "singleDeActivateSIMResult"
})
@XmlRootElement(name = "SingleDeActivateSIMResponse")
public class SingleDeActivateSIMResponse {

    @XmlElement(name = "SingleDeActivateSIMResult")
    protected ResponseType singleDeActivateSIMResult;

    /**
     * Gets the value of the singleDeActivateSIMResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getSingleDeActivateSIMResult() {
        return singleDeActivateSIMResult;
    }

    /**
     * Sets the value of the singleDeActivateSIMResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setSingleDeActivateSIMResult(ResponseType value) {
        this.singleDeActivateSIMResult = value;
    }

}
