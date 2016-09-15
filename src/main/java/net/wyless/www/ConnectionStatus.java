
package net.wyless.www;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConnectionStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConnectionStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Online"/>
 *     &lt;enumeration value="Offline"/>
 *     &lt;enumeration value="Updated"/>
 *     &lt;enumeration value="Unused"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConnectionStatus")
@XmlEnum
public enum ConnectionStatus {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Online")
    ONLINE("Online"),
    @XmlEnumValue("Offline")
    OFFLINE("Offline"),
    @XmlEnumValue("Updated")
    UPDATED("Updated"),
    @XmlEnumValue("Unused")
    UNUSED("Unused");
    private final String value;

    ConnectionStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConnectionStatus fromValue(String v) {
        for (ConnectionStatus c: ConnectionStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
