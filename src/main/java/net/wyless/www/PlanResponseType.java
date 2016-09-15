
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlanResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="OldPricePlanId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanResponseType", propOrder = {
    "oldPricePlanId"
})
public class PlanResponseType
    extends ResponseType
{

    @XmlElement(name = "OldPricePlanId")
    protected String oldPricePlanId;

    /**
     * Gets the value of the oldPricePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldPricePlanId() {
        return oldPricePlanId;
    }

    /**
     * Sets the value of the oldPricePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldPricePlanId(String value) {
        this.oldPricePlanId = value;
    }

}
