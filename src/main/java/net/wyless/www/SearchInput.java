
package net.wyless.www;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchInput">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:PorthosBase.enterprise.soap.wyless.com}PorthosBase">
 *       &lt;sequence>
 *         &lt;element name="TransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TableName" type="{https://www.wyless.net/}SearchTableName"/>
 *         &lt;element name="QueryFilter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInput", propOrder = {
    "transactionId",
    "tableName",
    "queryFilter"
})
@XmlSeeAlso({
    SearchResult.class
})
public class SearchInput
    extends PorthosBase
{

    @XmlElement(name = "TransactionId")
    protected String transactionId;
    @XmlElement(name = "TableName", required = true)
    protected SearchTableName tableName;
    @XmlElement(name = "QueryFilter")
    protected String queryFilter;

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

    /**
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link SearchTableName }
     *     
     */
    public SearchTableName getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchTableName }
     *     
     */
    public void setTableName(SearchTableName value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the queryFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryFilter() {
        return queryFilter;
    }

    /**
     * Sets the value of the queryFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryFilter(String value) {
        this.queryFilter = value;
    }

}
