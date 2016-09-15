
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkChangeSIMInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkChangeSIMInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}BulkPlanInputType">
 *       &lt;sequence>
 *         &lt;element name="NewSIMNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChangeReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReuseSIM" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkChangeSIMInputType", propOrder = {
    "newSIMNo",
    "changeReason",
    "reuseSIM"
})
public class BulkChangeSIMInputType
    extends BulkPlanInputType
{

    @XmlElement(name = "NewSIMNo")
    protected String newSIMNo;
    @XmlElement(name = "ChangeReason")
    protected String changeReason;
    @XmlElement(name = "ReuseSIM")
    protected boolean reuseSIM;

    /**
     * Gets the value of the newSIMNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewSIMNo() {
        return newSIMNo;
    }

    /**
     * Sets the value of the newSIMNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewSIMNo(String value) {
        this.newSIMNo = value;
    }

    /**
     * Gets the value of the changeReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * Sets the value of the changeReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeReason(String value) {
        this.changeReason = value;
    }

    /**
     * Gets the value of the reuseSIM property.
     * 
     */
    public boolean isReuseSIM() {
        return reuseSIM;
    }

    /**
     * Sets the value of the reuseSIM property.
     * 
     */
    public void setReuseSIM(boolean value) {
        this.reuseSIM = value;
    }

}
