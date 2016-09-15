
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlanInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlanInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}InputType">
 *       &lt;sequence>
 *         &lt;element name="PricePlanId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanInputType", propOrder = {
    "pricePlanId"
})
public class PlanInputType
    extends InputType
{

    @XmlElement(name = "PricePlanId")
    protected String pricePlanId;

    /**
     * Gets the value of the pricePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanId() {
        return pricePlanId;
    }

    /**
     * Sets the value of the pricePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanId(String value) {
        this.pricePlanId = value;
    }

}
