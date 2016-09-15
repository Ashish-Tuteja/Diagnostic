
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
 *         &lt;element name="ReleaseProfileResult" type="{https://www.wyless.net/}ProfileResponseType" minOccurs="0"/>
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
    "releaseProfileResult"
})
@XmlRootElement(name = "ReleaseProfileResponse")
public class ReleaseProfileResponse {

    @XmlElement(name = "ReleaseProfileResult")
    protected ProfileResponseType releaseProfileResult;

    /**
     * Gets the value of the releaseProfileResult property.
     * 
     * @return
     *     possible object is
     *     {@link ProfileResponseType }
     *     
     */
    public ProfileResponseType getReleaseProfileResult() {
        return releaseProfileResult;
    }

    /**
     * Sets the value of the releaseProfileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfileResponseType }
     *     
     */
    public void setReleaseProfileResult(ProfileResponseType value) {
        this.releaseProfileResult = value;
    }

}
