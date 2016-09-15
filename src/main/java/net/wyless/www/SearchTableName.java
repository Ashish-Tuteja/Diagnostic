
package net.wyless.www;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchTableName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchTableName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Profiles"/>
 *     &lt;enumeration value="MobileConnections"/>
 *     &lt;enumeration value="Users"/>
 *     &lt;enumeration value="Invoices"/>
 *     &lt;enumeration value="NetworkSegments"/>
 *     &lt;enumeration value="PricePlan"/>
 *     &lt;enumeration value="PPTP"/>
 *     &lt;enumeration value="IPSEC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchTableName")
@XmlEnum
public enum SearchTableName {

    @XmlEnumValue("Profiles")
    PROFILES("Profiles"),
    @XmlEnumValue("MobileConnections")
    MOBILE_CONNECTIONS("MobileConnections"),
    @XmlEnumValue("Users")
    USERS("Users"),
    @XmlEnumValue("Invoices")
    INVOICES("Invoices"),
    @XmlEnumValue("NetworkSegments")
    NETWORK_SEGMENTS("NetworkSegments"),
    @XmlEnumValue("PricePlan")
    PRICE_PLAN("PricePlan"),
    PPTP("PPTP"),
    IPSEC("IPSEC");
    private final String value;

    SearchTableName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchTableName fromValue(String v) {
        for (SearchTableName c: SearchTableName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
