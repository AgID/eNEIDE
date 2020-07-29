/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "criterionTypeCode", "name", "description", "legislationList", "subTenderingCriterionList", "tenderingCriterionPropertyGroupList"})
public class TenderingCriterion {
	private SchemeElement id;
	private ListElement criterionTypeCode;
	private String name;
	private String description;
	private List<Legislation> legislationList;
	private List<TenderingCriterion> subTenderingCriterionList;
	private List<TenderingCriterionPropertyGroup> tenderingCriterionPropertyGroupList;

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
	 * @return the criterionTypeCode
	 */
	public ListElement getCriterionTypeCode() {
		return criterionTypeCode;
	}

	/**
	 * @param criterionTypeCode the criterionTypeCode to set
	 */
	@XmlElement(name = "CriterionTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setCriterionTypeCode(ListElement criterionTypeCode) {
		this.criterionTypeCode = criterionTypeCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@XmlElement(name = "Name", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setName(String name) {
		this.name = name;
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
	 * @return the legislationList
	 */
	public List<Legislation> getLegislationList() {
		return legislationList;
	}

	/**
	 * @param legislationList the legislationList to set
	 */
	@XmlElement(name = "Legislation", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setLegislationList(List<Legislation> legislationList) {
		this.legislationList = legislationList;
	}

	/**
	 * @return the subTenderingCriterionList
	 */
	public List<TenderingCriterion> getSubTenderingCriterionList() {
		return subTenderingCriterionList;
	}

	/**
	 * @param subTenderingCriterionList the subTenderingCriterionList to set
	 */
	@XmlElement(name = "SubTenderingCriterion", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setSubTenderingCriterionList(List<TenderingCriterion> subTenderingCriterionList) {
		this.subTenderingCriterionList = subTenderingCriterionList;
	}

	/**
	 * @return the tenderingCriterionPropertyGroupList
	 */
	public List<TenderingCriterionPropertyGroup> getTenderingCriterionPropertyGroupList() {
		return tenderingCriterionPropertyGroupList;
	}

	/**
	 * @param tenderingCriterionPropertyGroupList the tenderingCriterionPropertyGroupList to set
	 */
	@XmlElement(name = "TenderingCriterionPropertyGroup", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setTenderingCriterionPropertyGroupList(List<TenderingCriterionPropertyGroup> tenderingCriterionPropertyGroupList) {
		this.tenderingCriterionPropertyGroupList = tenderingCriterionPropertyGroupList;
	}
}