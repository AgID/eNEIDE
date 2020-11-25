/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "QualificationApplicationRequest", namespace = "urn:oasis:names:specification:ubl:schema:xsd:QualificationApplicationRequest-2")
@XmlType(propOrder = {"ublVersionId", "customizationId", "profileId", "id", "uuid", "contractFolderId", "issueDate", "procedureCode", "qualificationApplicationTypeCode", "contractingParty", "tenderingCriterionList"})
public class QualificationApplicationRequest {
	private SchemeElement ublVersionId;
	private SchemeElement customizationId;
	private SchemeElement profileId;
	private SchemeElement id;
	private SchemeElement uuid;
	private SchemeElement contractFolderId;
	private String issueDate;
	private ListElement procedureCode;
	private ListElement qualificationApplicationTypeCode;
	private ContractingParty contractingParty;
	private List<TenderingCriterion> tenderingCriterionList;

	/**
	 * @return the ublVersionId
	 */
	public SchemeElement getUblVersionId() {
		return ublVersionId;
	}

	/**
	 * @param ublVersionId the ublVersionId to set
	 */
	@XmlElement(name = "UBLVersionID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setUblVersionId(SchemeElement ublVersionId) {
		this.ublVersionId = ublVersionId;
	}

	/**
	 * @return the customizationId
	 */
	public SchemeElement getCustomizationId() {
		return customizationId;
	}

	/**
	 * @param customizationId the customizationId to set
	 */
	@XmlElement(name = "CustomizationID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setCustomizationId(SchemeElement customizationId) {
		this.customizationId = customizationId;
	}

	/**
	 * @return the profileId
	 */
	public SchemeElement getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	@XmlElement(name = "ProfileID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setProfileId(SchemeElement profileId) {
		this.profileId = profileId;
	}

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
	 * @return the uuid
	 */
	public SchemeElement getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	@XmlElement(name = "UUID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setUuid(SchemeElement uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the contractFolderId
	 */
	public SchemeElement getContractFolderId() {
		return contractFolderId;
	}

	/**
	 * @param contractFolderId the contractFolderId to set
	 */
	@XmlElement(name = "ContractFolderID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setContractFolderId(SchemeElement contractFolderId) {
		this.contractFolderId = contractFolderId;
	}

	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	@XmlElement(name = "IssueDate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the procedureCode
	 */
	public ListElement getProcedureCode() {
		return procedureCode;
	}

	/**
	 * @param procedureCode the procedureCode to set
	 */
	@XmlElement(name = "ProcedureCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setProcedureCode(ListElement procedureCode) {
		this.procedureCode = procedureCode;
	}

	/**
	 * @return the qualificationApplicationTypeCode
	 */
	public ListElement getQualificationApplicationTypeCode() {
		return qualificationApplicationTypeCode;
	}

	/**
	 * @param qualificationApplicationTypeCode the qualificationApplicationTypeCode to set
	 */
	@XmlElement(name = "QualificationApplicationTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setQualificationApplicationTypeCode(ListElement qualificationApplicationTypeCode) {
		this.qualificationApplicationTypeCode = qualificationApplicationTypeCode;
	}

	/**
	 * @return the contractingParty
	 */
	public ContractingParty getContractingParty() {
		return contractingParty;
	}

	/**
	 * @param contractingParty the contractingParty to set
	 */
	@XmlElement(name = "ContractingParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setContractingParty(ContractingParty contractingParty) {
		this.contractingParty = contractingParty;
	}

	/**
	 * @return the tenderingCriterionList
	 */
	public List<TenderingCriterion> getTenderingCriterionList() {
		return tenderingCriterionList;
	}

	/**
	 * @param tenderingCriterionList the tenderingCriterionList to set
	 */
	@XmlElement(name = "TenderingCriterion", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setTenderingCriterionList(List<TenderingCriterion> tenderingCriterionList) {
		this.tenderingCriterionList = tenderingCriterionList;
	}
}