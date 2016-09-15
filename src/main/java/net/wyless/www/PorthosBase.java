
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PorthosBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PorthosBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PorthosInternalReference" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PorthosBase", namespace = "urn:PorthosBase.enterprise.soap.wyless.com", propOrder = {
    "id",
    "porthosInternalReference"
})
@XmlSeeAlso({
    BulkResponseType.class,
    SearchInput.class,
    Device.class,
    SearchObject.class,
    CommonBase.class,
    StatusInputType.class
})
public class PorthosBase {

    @XmlElement(name = "Id", required = true, nillable = true)
    protected String id;
    @XmlElement(name = "PorthosInternalReference")
    protected long porthosInternalReference;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the porthosInternalReference property.
     * 
     */
    public long getPorthosInternalReference() {
        return porthosInternalReference;
    }

    /**
     * Sets the value of the porthosInternalReference property.
     * 
     */
    public void setPorthosInternalReference(long value) {
        this.porthosInternalReference = value;
    }

}
