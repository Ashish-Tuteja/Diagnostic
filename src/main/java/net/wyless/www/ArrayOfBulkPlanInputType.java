
package net.wyless.www;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBulkPlanInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBulkPlanInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BulkPlanInputType" type="{https://www.wyless.net/}BulkPlanInputType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBulkPlanInputType", propOrder = {
    "bulkPlanInputType"
})
public class ArrayOfBulkPlanInputType {

    @XmlElement(name = "BulkPlanInputType", nillable = true)
    protected List<BulkPlanInputType> bulkPlanInputType;

    /**
     * Gets the value of the bulkPlanInputType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bulkPlanInputType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBulkPlanInputType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BulkPlanInputType }
     * 
     * 
     */
    public List<BulkPlanInputType> getBulkPlanInputType() {
        if (bulkPlanInputType == null) {
            bulkPlanInputType = new ArrayList<BulkPlanInputType>();
        }
        return this.bulkPlanInputType;
    }

}
