
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="objRequest" type="{https://www.wyless.net/}RegisterInputType" minOccurs="0"/>
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
    "objRequest"
})
@XmlRootElement(name = "UnRegisterDevice")
public class UnRegisterDevice {

    protected RegisterInputType objRequest;

    /**
     * Gets the value of the objRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterInputType }
     *     
     */
    public RegisterInputType getObjRequest() {
        return objRequest;
    }

    /**
     * Sets the value of the objRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterInputType }
     *     
     */
    public void setObjRequest(RegisterInputType value) {
        this.objRequest = value;
    }

}
