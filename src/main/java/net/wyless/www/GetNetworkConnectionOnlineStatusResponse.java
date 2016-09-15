
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetNetworkConnectionOnlineStatusResult" type="{https://www.wyless.net/}ConnectionStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getNetworkConnectionOnlineStatusResult"
})
@XmlRootElement(name = "GetNetworkConnectionOnlineStatusResponse")
public class GetNetworkConnectionOnlineStatusResponse {

    @XmlElement(name = "GetNetworkConnectionOnlineStatusResult", required = true)
    protected ConnectionStatus getNetworkConnectionOnlineStatusResult;

    /**
     * Gets the value of the getNetworkConnectionOnlineStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionStatus }
     *     
     */
    public ConnectionStatus getGetNetworkConnectionOnlineStatusResult() {
        return getNetworkConnectionOnlineStatusResult;
    }

    /**
     * Sets the value of the getNetworkConnectionOnlineStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionStatus }
     *     
     */
    public void setGetNetworkConnectionOnlineStatusResult(ConnectionStatus value) {
        this.getNetworkConnectionOnlineStatusResult = value;
    }

}
