package org.tei_c.ns._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tei_data.certainty.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tei_data.certainty">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="high"/>
 *     &lt;enumeration value="medium"/>
 *     &lt;enumeration value="low"/>
 *     &lt;enumeration value="unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tei_data.certainty")
@XmlEnum
public enum TeiDataCertainty {

    @XmlEnumValue("high")
    HIGH("high"),
    @XmlEnumValue("medium")
    MEDIUM("medium"),
    @XmlEnumValue("low")
    LOW("low"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown");
    private final String value;

    TeiDataCertainty(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TeiDataCertainty fromValue(String v) {
        for (TeiDataCertainty c: TeiDataCertainty.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
