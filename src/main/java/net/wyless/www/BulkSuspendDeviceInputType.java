
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BulkSuspendDeviceInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BulkSuspendDeviceInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.wyless.net/}BulkDeviceInputType">
 *       &lt;sequence>
 *         &lt;element name="ReuseForChangeDevice" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BulkSuspendDeviceInputType", propOrder = {
    "reuseForChangeDevice"
})
public class BulkSuspendDeviceInputType
    extends BulkDeviceInputType
{

    @XmlElement(name = "ReuseForChangeDevice")
    protected boolean reuseForChangeDevice;

    /**
     * Gets the value of the reuseForChangeDevice property.
     * 
     */
    public boolean isReuseForChangeDevice() {
        return reuseForChangeDevice;
    }

    /**
     * Sets the value of the reuseForChangeDevice property.
     * 
     */
    public void setReuseForChangeDevice(boolean value) {
        this.reuseForChangeDevice = value;
    }

}
