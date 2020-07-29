/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "propertyGroupTypeCode", "tenderingCriterionPropertyList", "subsidiaryTenderingCriterionPropertyGroupList"})
public class TenderingCriterionPropertyGroup {
	private SchemeElement id;
	private ListElement propertyGroupTypeCode;
	private List<TenderingCriterionProperty> tenderingCriterionPropertyList;
	private List<TenderingCriterionPropertyGroup> subsidiaryTenderingCriterionPropertyGroupList;

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
	 * @return the propertyGroupTypeCode
	 */
	public ListElement getPropertyGroupTypeCode() {
		return propertyGroupTypeCode;
	}

	/**
	 * @param propertyGroupTypeCode the propertyGroupTypeCode to set
	 */
	@XmlElement(name = "PropertyGroupTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setPropertyGroupTypeCode(ListElement propertyGroupTypeCode) {
		this.propertyGroupTypeCode = propertyGroupTypeCode;
	}

	/**
	 * @return the tenderingCriterionPropertyList
	 */
	public List<TenderingCriterionProperty> getTenderingCriterionPropertyList() {
		return tenderingCriterionPropertyList;
	}

	/**
	 * @param tenderingCriterionPropertyList the tenderingCriterionPropertyList to set
	 */
	@XmlElement(name = "TenderingCriterionProperty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setTenderingCriterionPropertyList(List<TenderingCriterionProperty> tenderingCriterionPropertyList) {
		this.tenderingCriterionPropertyList = tenderingCriterionPropertyList;
	}

	/**
	 * @return the subsidiaryTenderingCriterionPropertyGroupList
	 */
	public List<TenderingCriterionPropertyGroup> getSubsidiaryTenderingCriterionPropertyGroupList() {
		return subsidiaryTenderingCriterionPropertyGroupList;
	}

	/**
	 * @param subsidiaryTenderingCriterionPropertyGroupList the subsidiaryTenderingCriterionPropertyGroupList to set
	 */
	@XmlElement(name = "SubsidiaryTenderingCriterionPropertyGroup", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setSubsidiaryTenderingCriterionPropertyGroupList(List<TenderingCriterionPropertyGroup> subsidiaryTenderingCriterionPropertyGroupList) {
		this.subsidiaryTenderingCriterionPropertyGroupList = subsidiaryTenderingCriterionPropertyGroupList;
	}
}