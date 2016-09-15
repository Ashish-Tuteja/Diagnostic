
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
 *         &lt;element name="queryFilters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchTable" type="{https://www.wyless.net/}SearchTableName"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryFilters",
    "searchTable",
    "transactionId"
})
@XmlRootElement(name = "Search")
public class Search {

    protected String queryFilters;
    @XmlElement(required = true)
    protected SearchTableName searchTable;
    protected String transactionId;

    /**
     * Gets the value of the queryFilters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryFilters() {
        return queryFilters;
    }

    /**
     * Sets the value of the queryFilters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryFilters(String value) {
        this.queryFilters = value;
    }

    /**
     * Gets the value of the searchTable property.
     * 
     * @return
     *     possible object is
     *     {@link SearchTableName }
     *     
     */
    public SearchTableName getSearchTable() {
        return searchTable;
    }

    /**
     * Sets the value of the searchTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchTableName }
     *     
     */
    public void setSearchTable(SearchTableName value) {
        this.searchTable = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

}
