
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
 *         &lt;element name="AssignProfileResult" type="{https://www.wyless.net/}ProfileResponseType" minOccurs="0"/>
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
    "assignProfileResult"
})
@XmlRootElement(name = "AssignProfileResponse")
public class AssignProfileResponse {

    @XmlElement(name = "AssignProfileResult")
    protected ProfileResponseType assignProfileResult;

    /**
     * Gets the value of the assignProfileResult property.
     * 
     * @return
     *     possible object is
     *     {@link ProfileResponseType }
     *     
     */
    public ProfileResponseType getAssignProfileResult() {
        return assignProfileResult;
    }

    /**
     * Sets the value of the assignProfileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfileResponseType }
     *     
     */
    public void setAssignProfileResult(ProfileResponseType value) {
        this.assignProfileResult = value;
    }

}
