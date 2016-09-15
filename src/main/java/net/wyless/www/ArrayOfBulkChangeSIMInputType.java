
package net.wyless.www;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBulkChangeSIMInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBulkChangeSIMInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BulkChangeSIMInputType" type="{https://www.wyless.net/}BulkChangeSIMInputType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBulkChangeSIMInputType", propOrder = {
    "bulkChangeSIMInputType"
})
public class ArrayOfBulkChangeSIMInputType {

    @XmlElement(name = "BulkChangeSIMInputType", nillable = true)
    protected List<BulkChangeSIMInputType> bulkChangeSIMInputType;

    /**
     * Gets the value of the bulkChangeSIMInputType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bulkChangeSIMInputType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBulkChangeSIMInputType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BulkChangeSIMInputType }
     * 
     * 
     */
    public List<BulkChangeSIMInputType> getBulkChangeSIMInputType() {
        if (bulkChangeSIMInputType == null) {
            bulkChangeSIMInputType = new ArrayList<BulkChangeSIMInputType>();
        }
        return this.bulkChangeSIMInputType;
    }

}
