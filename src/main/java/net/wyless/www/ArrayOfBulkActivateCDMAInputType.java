
package net.wyless.www;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBulkActivateCDMAInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBulkActivateCDMAInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BulkActivateCDMAInputType" type="{https://www.wyless.net/}BulkActivateCDMAInputType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBulkActivateCDMAInputType", propOrder = {
    "bulkActivateCDMAInputType"
})
public class ArrayOfBulkActivateCDMAInputType {

    @XmlElement(name = "BulkActivateCDMAInputType", nillable = true)
    protected List<BulkActivateCDMAInputType> bulkActivateCDMAInputType;

    /**
     * Gets the value of the bulkActivateCDMAInputType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bulkActivateCDMAInputType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBulkActivateCDMAInputType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BulkActivateCDMAInputType }
     * 
     * 
     */
    public List<BulkActivateCDMAInputType> getBulkActivateCDMAInputType() {
        if (bulkActivateCDMAInputType == null) {
            bulkActivateCDMAInputType = new ArrayList<BulkActivateCDMAInputType>();
        }
        return this.bulkActivateCDMAInputType;
    }

}
