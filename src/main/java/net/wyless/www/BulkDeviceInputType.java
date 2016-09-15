
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkDeviceInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkDeviceInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}Device">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkDeviceInputType")
@XmlSeeAlso({
    BulkActivateCDMAInputType.class,
    BulkSuspendDeviceInputType.class
})
public class BulkDeviceInputType
    extends Device
{


}
