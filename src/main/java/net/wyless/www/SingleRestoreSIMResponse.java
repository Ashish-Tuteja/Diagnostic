
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
 *         &lt;element name="SingleRestoreSIMResult" type="{https://www.wyless.net/}ResponseType" minOccurs="0"/>
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
    "singleRestoreSIMResult"
})
@XmlRootElement(name = "SingleRestoreSIMResponse")
public class SingleRestoreSIMResponse {

    @XmlElement(name = "SingleRestoreSIMResult")
    protected ResponseType singleRestoreSIMResult;

    /**
     * Gets the value of the singleRestoreSIMResult property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getSingleRestoreSIMResult() {
        return singleRestoreSIMResult;
    }

    /**
     * Sets the value of the singleRestoreSIMResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setSingleRestoreSIMResult(ResponseType value) {
        this.singleRestoreSIMResult = value;
    }

}
