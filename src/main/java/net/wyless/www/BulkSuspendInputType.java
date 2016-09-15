
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkSuspendInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkSuspendInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}BulkInputType">
 *       &lt;sequence>
 *         &lt;element name="ReuseForChangeSIM" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkSuspendInputType", propOrder = {
    "reuseForChangeSIM"
})
public class BulkSuspendInputType
    extends BulkInputType
{

    @XmlElement(name = "ReuseForChangeSIM")
    protected boolean reuseForChangeSIM;

    /**
     * Gets the value of the reuseForChangeSIM property.
     * 
     */
    public boolean isReuseForChangeSIM() {
        return reuseForChangeSIM;
    }

    /**
     * Sets the value of the reuseForChangeSIM property.
     * 
     */
    public void setReuseForChangeSIM(boolean value) {
        this.reuseForChangeSIM = value;
    }

}
