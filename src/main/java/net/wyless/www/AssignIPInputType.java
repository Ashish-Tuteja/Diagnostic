
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignIPInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignIPInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}IPInputType">
 *       &lt;sequence>
 *         &lt;element name="SegmentIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignIPInputType", propOrder = {
    "segmentIP"
})
public class AssignIPInputType
    extends IPInputType
{

    @XmlElement(name = "SegmentIP")
    protected String segmentIP;

    /**
     * Gets the value of the segmentIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentIP() {
        return segmentIP;
    }

    /**
     * Sets the value of the segmentIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentIP(String value) {
        this.segmentIP = value;
    }

}
