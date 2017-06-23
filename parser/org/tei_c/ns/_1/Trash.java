//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.06.23 时间 04:21:10 AM CEST 
//


package org.tei_c.ns._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.tei-c.org/ns/1.0}ref" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.tei-c.org/ns/1.0}tei_att.editLike.attributes"/>
 *       &lt;attGroup ref="{http://www.tei-c.org/ns/1.0}tei_att.datable.attributes"/>
 *       &lt;attGroup ref="{http://www.tei-c.org/ns/1.0}tei_att.naming.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "trash")
public class Trash {

    @XmlElementRef(name = "ref", namespace = "http://www.tei-c.org/ns/1.0", type = Ref.class, required = false)
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "evidence")
    protected List<String> evidences;
    @XmlAttribute(name = "instant")
    protected String instant;
    @XmlAttribute(name = "source")
    protected List<String> sources;
    @XmlAttribute(name = "min")
    protected String min;
    @XmlAttribute(name = "max")
    protected String max;
    @XmlAttribute(name = "atLeast")
    protected String atLeast;
    @XmlAttribute(name = "atMost")
    protected String atMost;
    @XmlAttribute(name = "confidence")
    protected Double confidence;
    @XmlAttribute(name = "extent")
    protected String extent;
    @XmlAttribute(name = "quantity")
    protected String quantity;
    @XmlAttribute(name = "scope")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String scope;
    @XmlAttribute(name = "unit")
    protected String unit;
    @XmlAttribute(name = "precision")
    protected TeiDataCertainty precision;
    @XmlAttribute(name = "notBefore-custom")
    protected List<String> notBeforeCustoms;
    @XmlAttribute(name = "from-custom")
    protected List<String> fromCustoms;
    @XmlAttribute(name = "notAfter-custom")
    protected List<String> notAfterCustoms;
    @XmlAttribute(name = "when-custom")
    protected List<String> whenCustoms;
    @XmlAttribute(name = "datingPoint")
    protected String datingPoint;
    @XmlAttribute(name = "to-custom")
    protected List<String> toCustoms;
    @XmlAttribute(name = "datingMethod")
    protected String datingMethod;
    @XmlAttribute(name = "when")
    protected String when;
    @XmlAttribute(name = "notBefore")
    protected String notBefore;
    @XmlAttribute(name = "from")
    protected String from;
    @XmlAttribute(name = "notAfter")
    protected String notAfter;
    @XmlAttribute(name = "to")
    protected String to;
    @XmlAttribute(name = "period")
    protected String period;
    @XmlAttribute(name = "to-iso")
    protected String toIso;
    @XmlAttribute(name = "from-iso")
    protected String fromIso;
    @XmlAttribute(name = "when-iso")
    protected String whenIso;
    @XmlAttribute(name = "notBefore-iso")
    protected String notBeforeIso;
    @XmlAttribute(name = "notAfter-iso")
    protected String notAfterIso;
    @XmlAttribute(name = "calendar")
    protected String calendar;
    @XmlAttribute(name = "key")
    protected String key;
    @XmlAttribute(name = "ref")
    protected List<String> reves;
    @XmlAttribute(name = "role")
    protected List<String> roles;
    @XmlAttribute(name = "nymRef")
    protected List<String> nymReves;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ref }
     * {@link String }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the evidences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evidences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvidences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEvidences() {
        if (evidences == null) {
            evidences = new ArrayList<String>();
        }
        return this.evidences;
    }

    /**
     * 获取instant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstant() {
        if (instant == null) {
            return "false";
        } else {
            return instant;
        }
    }

    /**
     * 设置instant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstant(String value) {
        this.instant = value;
    }

    /**
     * Gets the value of the sources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSources() {
        if (sources == null) {
            sources = new ArrayList<String>();
        }
        return this.sources;
    }

    /**
     * 获取min属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMin() {
        return min;
    }

    /**
     * 设置min属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMin(String value) {
        this.min = value;
    }

    /**
     * 获取max属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMax() {
        return max;
    }

    /**
     * 设置max属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMax(String value) {
        this.max = value;
    }

    /**
     * 获取atLeast属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtLeast() {
        return atLeast;
    }

    /**
     * 设置atLeast属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtLeast(String value) {
        this.atLeast = value;
    }

    /**
     * 获取atMost属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtMost() {
        return atMost;
    }

    /**
     * 设置atMost属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtMost(String value) {
        this.atMost = value;
    }

    /**
     * 获取confidence属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * 设置confidence属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setConfidence(Double value) {
        this.confidence = value;
    }

    /**
     * 获取extent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtent() {
        return extent;
    }

    /**
     * 设置extent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtent(String value) {
        this.extent = value;
    }

    /**
     * 获取quantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * 获取scope属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置scope属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
    }

    /**
     * 获取unit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置unit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * 获取precision属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TeiDataCertainty }
     *     
     */
    public TeiDataCertainty getPrecision() {
        return precision;
    }

    /**
     * 设置precision属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TeiDataCertainty }
     *     
     */
    public void setPrecision(TeiDataCertainty value) {
        this.precision = value;
    }

    /**
     * Gets the value of the notBeforeCustoms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notBeforeCustoms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotBeforeCustoms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNotBeforeCustoms() {
        if (notBeforeCustoms == null) {
            notBeforeCustoms = new ArrayList<String>();
        }
        return this.notBeforeCustoms;
    }

    /**
     * Gets the value of the fromCustoms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fromCustoms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFromCustoms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFromCustoms() {
        if (fromCustoms == null) {
            fromCustoms = new ArrayList<String>();
        }
        return this.fromCustoms;
    }

    /**
     * Gets the value of the notAfterCustoms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notAfterCustoms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotAfterCustoms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNotAfterCustoms() {
        if (notAfterCustoms == null) {
            notAfterCustoms = new ArrayList<String>();
        }
        return this.notAfterCustoms;
    }

    /**
     * Gets the value of the whenCustoms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the whenCustoms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWhenCustoms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getWhenCustoms() {
        if (whenCustoms == null) {
            whenCustoms = new ArrayList<String>();
        }
        return this.whenCustoms;
    }

    /**
     * 获取datingPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatingPoint() {
        return datingPoint;
    }

    /**
     * 设置datingPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatingPoint(String value) {
        this.datingPoint = value;
    }

    /**
     * Gets the value of the toCustoms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the toCustoms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getToCustoms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getToCustoms() {
        if (toCustoms == null) {
            toCustoms = new ArrayList<String>();
        }
        return this.toCustoms;
    }

    /**
     * 获取datingMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatingMethod() {
        return datingMethod;
    }

    /**
     * 设置datingMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatingMethod(String value) {
        this.datingMethod = value;
    }

    /**
     * 获取when属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhen() {
        return when;
    }

    /**
     * 设置when属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhen(String value) {
        this.when = value;
    }

    /**
     * 获取notBefore属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotBefore() {
        return notBefore;
    }

    /**
     * 设置notBefore属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotBefore(String value) {
        this.notBefore = value;
    }

    /**
     * 获取from属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * 设置from属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * 获取notAfter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotAfter() {
        return notAfter;
    }

    /**
     * 设置notAfter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotAfter(String value) {
        this.notAfter = value;
    }

    /**
     * 获取to属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * 设置to属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * 获取period属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod() {
        return period;
    }

    /**
     * 设置period属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod(String value) {
        this.period = value;
    }

    /**
     * 获取toIso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToIso() {
        return toIso;
    }

    /**
     * 设置toIso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToIso(String value) {
        this.toIso = value;
    }

    /**
     * 获取fromIso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromIso() {
        return fromIso;
    }

    /**
     * 设置fromIso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromIso(String value) {
        this.fromIso = value;
    }

    /**
     * 获取whenIso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhenIso() {
        return whenIso;
    }

    /**
     * 设置whenIso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhenIso(String value) {
        this.whenIso = value;
    }

    /**
     * 获取notBeforeIso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotBeforeIso() {
        return notBeforeIso;
    }

    /**
     * 设置notBeforeIso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotBeforeIso(String value) {
        this.notBeforeIso = value;
    }

    /**
     * 获取notAfterIso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotAfterIso() {
        return notAfterIso;
    }

    /**
     * 设置notAfterIso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotAfterIso(String value) {
        this.notAfterIso = value;
    }

    /**
     * 获取calendar属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendar() {
        return calendar;
    }

    /**
     * 设置calendar属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendar(String value) {
        this.calendar = value;
    }

    /**
     * 获取key属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the reves property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reves property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReves().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getReves() {
        if (reves == null) {
            reves = new ArrayList<String>();
        }
        return this.reves;
    }

    /**
     * Gets the value of the roles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRoles() {
        if (roles == null) {
            roles = new ArrayList<String>();
        }
        return this.roles;
    }

    /**
     * Gets the value of the nymReves property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nymReves property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNymReves().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNymReves() {
        if (nymReves == null) {
            nymReves = new ArrayList<String>();
        }
        return this.nymReves;
    }

}
