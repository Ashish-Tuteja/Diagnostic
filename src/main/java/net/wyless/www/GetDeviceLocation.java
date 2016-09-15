
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
 *         &lt;element name="objRequest" type="{https://www.wyless.net/}LocationInputType" minOccurs="0"/>
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
@XmlRootElement(name = "GetDeviceLocation")
public class GetDeviceLocation {

    protected LocationInputType objRequest;

    /**
     * Gets the value of the objRequest property.
     * 
     * @return
     *     possible object is
     *     {@link LocationInputType }
     *     
     */
    public LocationInputType getObjRequest() {
        return objRequest;
    }

    /**
     * Sets the value of the objRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationInputType }
     *     
     */
    public void setObjRequest(LocationInputType value) {
        this.objRequest = value;
    }

}
