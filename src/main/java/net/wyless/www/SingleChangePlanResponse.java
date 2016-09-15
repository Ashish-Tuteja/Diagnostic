
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
 *         &lt;element name="SingleChangePlanResult" type="{https://www.wyless.net/}PlanResponseType" minOccurs="0"/>
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
    "singleChangePlanResult"
})
@XmlRootElement(name = "SingleChangePlanResponse")
public class SingleChangePlanResponse {

    @XmlElement(name = "SingleChangePlanResult")
    protected PlanResponseType singleChangePlanResult;

    /**
     * Gets the value of the singleChangePlanResult property.
     * 
     * @return
     *     possible object is
     *     {@link PlanResponseType }
     *     
     */
    public PlanResponseType getSingleChangePlanResult() {
        return singleChangePlanResult;
    }

    /**
     * Sets the value of the singleChangePlanResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanResponseType }
     *     
     */
    public void setSingleChangePlanResult(PlanResponseType value) {
        this.singleChangePlanResult = value;
    }

}
