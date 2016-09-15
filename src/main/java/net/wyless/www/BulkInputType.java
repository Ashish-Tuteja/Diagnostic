
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}SIM">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkInputType")
@XmlSeeAlso({
    BulkSuspendInputType.class,
    BulkActivateInputType.class,
    BulkPlanInputType.class,
    BulkReActivateInputType.class
})
public class BulkInputType
    extends SIM
{


}
