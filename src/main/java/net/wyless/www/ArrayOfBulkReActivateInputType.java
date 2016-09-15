
package net.wyless.www;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBulkReActivateInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBulkReActivateInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BulkReActivateInputType" type="{https://www.wyless.net/}BulkReActivateInputType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBulkReActivateInputType", propOrder = {
    "bulkReActivateInputType"
})
public class ArrayOfBulkReActivateInputType {

    @XmlElement(name = "BulkReActivateInputType", nillable = true)
    protected List<BulkReActivateInputType> bulkReActivateInputType;

    /**
     * Gets the value of the bulkReActivateInputType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bulkReActivateInputType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBulkReActivateInputType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BulkReActivateInputType }
     * 
     * 
     */
    public List<BulkReActivateInputType> getBulkReActivateInputType() {
        if (bulkReActivateInputType == null) {
            bulkReActivateInputType = new ArrayList<BulkReActivateInputType>();
        }
        return this.bulkReActivateInputType;
    }

}
