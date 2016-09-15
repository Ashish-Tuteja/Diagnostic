
package net.wyless.www;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBulkActivateInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBulkActivateInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BulkActivateInputType" type="{https://www.wyless.net/}BulkActivateInputType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBulkActivateInputType", propOrder = {
    "bulkActivateInputType"
})
public class ArrayOfBulkActivateInputType {

    @XmlElement(name = "BulkActivateInputType", nillable = true)
    protected List<BulkActivateInputType> bulkActivateInputType;

    /**
     * Gets the value of the bulkActivateInputType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bulkActivateInputType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBulkActivateInputType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BulkActivateInputType }
     * 
     * 
     */
    public List<BulkActivateInputType> getBulkActivateInputType() {
        if (bulkActivateInputType == null) {
            bulkActivateInputType = new ArrayList<BulkActivateInputType>();
        }
        return this.bulkActivateInputType;
    }

}
