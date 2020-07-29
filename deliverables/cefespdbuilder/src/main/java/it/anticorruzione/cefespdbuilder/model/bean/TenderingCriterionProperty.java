/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "description", "typeCode", "valueDataTypeCode"})
public class TenderingCriterionProperty {
	private SchemeElement id;
	private String description;
	private ListElement typeCode;
	private ListElement valueDataTypeCode;

	/**
	 * @return the id
	 */
	public SchemeElement getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setId(SchemeElement id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	@XmlElement(name = "Description", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the typeCode
	 */
	public ListElement getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	@XmlElement(name = "TypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setTypeCode(ListElement typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the valueDataTypeCode
	 */
	public ListElement getValueDataTypeCode() {
		return valueDataTypeCode;
	}

	/**
	 * @param valueDataTypeCode the valueDataTypeCode to set
	 */
	@XmlElement(name = "ValueDataTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setValueDataTypeCode(ListElement valueDataTypeCode) {
		this.valueDataTypeCode = valueDataTypeCode;
	}
}