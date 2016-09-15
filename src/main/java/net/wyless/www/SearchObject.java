
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchObject">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:PorthosBase.enterprise.soap.wyless.com}PorthosBase">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchObject")
@XmlSeeAlso({
    User.class,
    NetworkSegment.class,
    MobileConnection.class,
    PPTPConnection.class,
    IPSec.class,
    Invoice.class,
    ProfileInfo.class,
    PricePlan.class
})
public class SearchObject
    extends PorthosBase
{


}
