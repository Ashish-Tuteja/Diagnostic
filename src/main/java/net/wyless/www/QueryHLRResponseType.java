
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryHLRResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryHLRResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="SOC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="APNNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="APNValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MSStatusInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryHLRResponseType", propOrder = {
    "soc",
    "apnNames",
    "apnValues",
    "msStatusInfo"
})
public class QueryHLRResponseType
    extends ResponseType
{

    @XmlElement(name = "SOC")
    protected String soc;
    @XmlElement(name = "APNNames")
    protected String apnNames;
    @XmlElement(name = "APNValues")
    protected String apnValues;
    @XmlElement(name = "MSStatusInfo")
    protected String msStatusInfo;

    /**
     * Gets the value of the soc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOC() {
        return soc;
    }

    /**
     * Sets the value of the soc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOC(String value) {
        this.soc = value;
    }

    /**
     * Gets the value of the apnNames property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPNNames() {
        return apnNames;
    }

    /**
     * Sets the value of the apnNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPNNames(String value) {
        this.apnNames = value;
    }

    /**
     * Gets the value of the apnValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPNValues() {
        return apnValues;
    }

    /**
     * Sets the value of the apnValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPNValues(String value) {
        this.apnValues = value;
    }

    /**
     * Gets the value of the msStatusInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSStatusInfo() {
        return msStatusInfo;
    }

    /**
     * Sets the value of the msStatusInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSStatusInfo(String value) {
        this.msStatusInfo = value;
    }

}
