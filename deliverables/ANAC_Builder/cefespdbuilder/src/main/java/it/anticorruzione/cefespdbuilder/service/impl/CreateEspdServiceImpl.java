/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.service.impl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import it.anticorruzione.cefespdbuilder.model.bean.ContractingParty;
import it.anticorruzione.cefespdbuilder.model.bean.Country;
import it.anticorruzione.cefespdbuilder.model.bean.EspdRequest;
import it.anticorruzione.cefespdbuilder.model.bean.Legislation;
import it.anticorruzione.cefespdbuilder.model.bean.ListElement;
import it.anticorruzione.cefespdbuilder.model.bean.Party;
import it.anticorruzione.cefespdbuilder.model.bean.PartyIdentification;
import it.anticorruzione.cefespdbuilder.model.bean.PartyName;
import it.anticorruzione.cefespdbuilder.model.bean.PostalAddress;
import it.anticorruzione.cefespdbuilder.model.bean.QualificationApplicationRequest;
import it.anticorruzione.cefespdbuilder.model.bean.SchemeElement;
import it.anticorruzione.cefespdbuilder.model.bean.TenderingCriterion;
import it.anticorruzione.cefespdbuilder.model.bean.TenderingCriterionProperty;
import it.anticorruzione.cefespdbuilder.model.bean.TenderingCriterionPropertyGroup;
import it.anticorruzione.cefespdbuilder.service.CreateEspdService;

@Service
public class CreateEspdServiceImpl implements CreateEspdService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateEspdService.class.getName());

	@Value("${cefecertisUrl}")
	private String cefecertisUrl;

	@Value("${proxyEnable}")
	private Boolean proxyEnable;

	@Value("${proxyHost}")
	private String proxyHost;

	@Value("${proxyPort}")
	private Integer proxyPort;

	@Value("${connectTimeout}")
	private Integer connectTimeout;

	@Value("${readTimeout}")
	private Integer readTimeout;

	@Value("${retriesNumber}")
	private Integer retriesNumber;

	@Value("${delay}")
	private Integer delay;

	public QualificationApplicationRequest createEspd(EspdRequest espdRequest, String nationalEntity) {
		LOGGER.info("--- Begin createEspd ---");

		try {
			QualificationApplicationRequest qualificationApplicationRequest = new QualificationApplicationRequest();

			// --- UBLVersionID --- //
			SchemeElement ublVersionId = new SchemeElement();
			ublVersionId.setValue("2.2");
			ublVersionId.setSchemeAgencyId("OASIS-UBL-TC");

			qualificationApplicationRequest.setUblVersionId(ublVersionId);
			// --- UBLVersionID --- //

			// --- CustomizationID --- //
			SchemeElement customizationId = new SchemeElement();
			customizationId.setValue("urn:www.cenbii.eu:transaction:biitrdm070:ver3.0");
			customizationId.setSchemeAgencyId("CEN-BII");

			qualificationApplicationRequest.setCustomizationId(customizationId);
			// --- CustomizationID --- //

			// --- ProfileID --- //
			SchemeElement profileId = new SchemeElement();
			profileId.setValue("4.1");
			profileId.setSchemeAgencyId("CEN-BII");

			qualificationApplicationRequest.setProfileId(profileId);
			// --- ProfileID --- //

			// --- ID --- //
			SchemeElement qualificationApplicationRequestId = new SchemeElement();
			qualificationApplicationRequestId.setValue("");
			qualificationApplicationRequestId.setSchemeAgencyId("DGPE");

			qualificationApplicationRequest.setId(qualificationApplicationRequestId);
			// --- ID --- //

			// --- UUID --- //
			SchemeElement uuid = new SchemeElement();
			uuid.setValue(espdRequest.getCodiceIdentificativoGara());
			uuid.setSchemeAgencyId("EU-COM-GROW");

			qualificationApplicationRequest.setUuid(uuid);
			// --- UUID --- //

			// --- ContractFolderID --- //
			SchemeElement contractFolderId = new SchemeElement();
			contractFolderId.setValue("");
			contractFolderId.setSchemeAgencyId("DGPE");

			qualificationApplicationRequest.setContractFolderId(contractFolderId);
			// --- ContractFolderID --- //

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			qualificationApplicationRequest.setIssueDate(simpleDateFormat.format(new Date()));

			// --- ProcedureCode --- //
			ListElement procedureCode = new ListElement();
			procedureCode.setValue("AWARD_WO_PUB");
			procedureCode.setListId("ProcedureType");
			procedureCode.setListAgencyId("EU-COM-OP");
			procedureCode.setListVersionId("1.0");

			qualificationApplicationRequest.setProcedureCode(procedureCode);
			// --- ProcedureCode --- //

			// --- QualificationApplicationTypeCode --- //
			ListElement qualificationApplicationTypeCode = new ListElement();
			qualificationApplicationTypeCode.setValue("Extended");
			qualificationApplicationTypeCode.setListId("QualificationApplicationType");
			qualificationApplicationTypeCode.setListAgencyId("EU-COM-GROW");
			qualificationApplicationTypeCode.setListVersionId("2.1.1");

			qualificationApplicationRequest.setQualificationApplicationTypeCode(qualificationApplicationTypeCode);
			// --- QualificationApplicationTypeCode --- //

			// --- ContractingParty --- //

			// --- PartyIdentification --- //
			SchemeElement partyIdentificationId = new SchemeElement();
			partyIdentificationId.setValue(espdRequest.getPartyIdentification());
			partyIdentificationId.setSchemeAgencyId("EU-COM-GROW");

			PartyIdentification partyIdentification = new PartyIdentification();
			partyIdentification.setId(partyIdentificationId);
			// --- PartyIdentification --- //

			// --- PartyName --- //
			PartyName partyName = new PartyName();
			partyName.setName(espdRequest.getPartyName());
			// --- PartyName --- //

			// --- IdentificationCode --- //
			ListElement identificationCode = new ListElement();
			identificationCode.setValue("IT");
			identificationCode.setListId("CountryCodeIdentifier");
			identificationCode.setListAgencyId("ISO");
			identificationCode.setListVersionId("1.0");
			// --- IdentificationCode --- //

			// --- Country --- //
			Country country = new Country();
			country.setIdentificationCode(identificationCode);
			// --- Country --- //

			// --- PostalAddress --- //
			PostalAddress postalAddress = new PostalAddress();
			postalAddress.setStreetName(espdRequest.getStreet());
			postalAddress.setCityName(espdRequest.getCity());
			postalAddress.setPostalZone(espdRequest.getPostalZone());
			postalAddress.setCountry(country);
			// --- PostalAddress --- //

			// --- Party --- //
			Party party = new Party();
			party.setPartyIdentification(partyIdentification);
			party.setPartyName(partyName);
			party.setPostalAddress(postalAddress);
			// --- Party --- //

			ContractingParty contractingParty = new ContractingParty();
			contractingParty.setParty(party);

			qualificationApplicationRequest.setContractingParty(contractingParty);
			// --- ContractingParty --- //

			List<TenderingCriterion> tenderingCriterionList = new ArrayList<>();

			SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();

			if(proxyEnable) {
				simpleClientHttpRequestFactory.setProxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
			}

			simpleClientHttpRequestFactory.setConnectTimeout(connectTimeout);
			simpleClientHttpRequestFactory.setReadTimeout(readTimeout);

			RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);

			List<?> criterionResultList;

			if(nationalEntity != null) {
				criterionResultList = (List<?>) getForObject(restTemplate, (cefecertisUrl + "/api/v1/criteriaTaxonomy?subCriteriaNationalEntity=" + nationalEntity), retriesNumber);
			} else {
				criterionResultList = (List<?>) getForObject(restTemplate, (cefecertisUrl + "/api/v1/criteriaTaxonomy"), retriesNumber);
			}

			for(int criterionCont = 0 ; criterionCont < criterionResultList.size() ; criterionCont++) {
				Map<?, ?> criterionMap = (Map<?, ?>) criterionResultList.get(criterionCont);

				String id = (String) criterionMap.get("id");
				Integer criteriaGroupId = (Integer) criterionMap.get("criteriaGroupId");

				if((criteriaGroupId >= 3 && criteriaGroupId <= 6) || (espdRequest.getCriteriaEspd() != null && espdRequest.getCriteriaEspd().contains(criterionMap.get("id")))) {
					TenderingCriterion tenderingCriterion = populateTenderingCriterion(criterionMap, nationalEntity);

					if(!tenderingCriterion.getTenderingCriterionPropertyGroupList().isEmpty()) {
						tenderingCriterionList.add(tenderingCriterion);

						LOGGER.info("criterion {} of {} - id : \"{}\" - SELECTED - typeCode : {}", (criterionCont + 1), criterionResultList.size(), id, criterionMap.get("typeCode"));
					} else {
						LOGGER.info("criterion {} of {} - id : \"{}\" - NO TAXONOMY FOUND", (criterionCont + 1), criterionResultList.size(), id);
					}
				} else {
					LOGGER.info("criterion {} of {} - id : \"{}\" - NOT SELECTED - typeCode : {}", (criterionCont + 1), criterionResultList.size(), id, criterionMap.get("typeCode"));
				}
			}

			qualificationApplicationRequest.setTenderingCriterionList(tenderingCriterionList);

			LOGGER.info("--- End createEspd ---");

			return qualificationApplicationRequest;
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
		}

		return null;
	}

	private Object getForObject(RestTemplate restTemplate, String url, int retriesNumber) {
		try {
			return restTemplate.getForObject(url, Object.class);
		} catch (Exception exception) {
			if(retriesNumber > 0) {
				retriesNumber--;

				try {
					Thread.sleep(delay);
				} catch (InterruptedException interruptedException) {
					LOGGER.error(interruptedException.getMessage(), interruptedException);

					Thread.currentThread().interrupt();
				}

				return getForObject(restTemplate, url, retriesNumber);
			} else {
				LOGGER.error(exception.getMessage(), exception);

				throw exception;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private TenderingCriterion populateTenderingCriterion(Map<?, ?> criterionMap, String nationalEntity) {
		TenderingCriterion tenderingCriterion = new TenderingCriterion();

		// --- ID --- //
		SchemeElement tenderingCriterionId = new SchemeElement();
		tenderingCriterionId.setValue((String) criterionMap.get("id"));
		tenderingCriterionId.setSchemeId("CriteriaTaxonomy");
		tenderingCriterionId.setSchemeAgencyId("EU-COM-GROW");
		tenderingCriterionId.setSchemeVersionId("2.1.1");

		tenderingCriterion.setId(tenderingCriterionId);
		// --- ID --- //

		// --- CriterionTypeCode --- //
		ListElement criterionTypeCode = new ListElement();
		criterionTypeCode.setValue((String) criterionMap.get("typeCode"));
		criterionTypeCode.setListId("CriteriaTypeCode");
		criterionTypeCode.setListAgencyId("EU-COM-GROW");
		criterionTypeCode.setListVersionId("2.1.1");

		tenderingCriterion.setCriterionTypeCode(criterionTypeCode);
		// --- CriterionTypeCode --- //

		tenderingCriterion.setName((String) criterionMap.get("name"));

		// --- Description --- //
		if(criterionMap.get("description") != null) {
			tenderingCriterion.setDescription((String) criterionMap.get("description"));
		} else {
			tenderingCriterion.setDescription("No description found !");
		}
		// --- Description --- //

		// --- Legislation --- //
		List<Legislation> resultList = new ArrayList<>();

		List<?> legislationReferenceList = (List<?>) criterionMap.get("legislationReferences");

		if(!criterionMap.get("nationalEntity").toString().equals("eu") && legislationReferenceList != null) {
			for(int legislationReferenceCont = 0 ; legislationReferenceCont < legislationReferenceList.size() ; legislationReferenceCont++) {
				Map<?, ?> legislationReferenceMap = (Map<?, ?>) legislationReferenceList.get(legislationReferenceCont);

				Legislation legislation = new Legislation();

				// --- ID --- //
				SchemeElement legislationId = new SchemeElement();
				legislationId.setValue((String) legislationReferenceMap.get("id"));
				legislationId.setSchemeId("CriteriaTaxonomy");
				legislationId.setSchemeAgencyId("EU-COM-GROW");
				legislationId.setSchemeVersionId("2.1.1");

				legislation.setId(legislationId);
				// --- ID --- //

				legislation.setTitle((String) legislationReferenceMap.get("title"));
				legislation.setDescription((String) legislationReferenceMap.get("description"));
				legislation.setJurisdictionLevel((String) legislationReferenceMap.get("jurisdictionLevelCode"));
				legislation.setArticle((String) legislationReferenceMap.get("article"));
				legislation.setUri((String) legislationReferenceMap.get("uri"));

				resultList.add(legislation);
			}

			tenderingCriterion.setLegislationList(resultList);
		}
		// --- Legislation --- //

		// --- SubTenderingCriterion --- //
		if(nationalEntity != null) {
			List<TenderingCriterion> subTenderingCriterionList = new ArrayList<>();

			List<?> subCriterionList = (List<?>) criterionMap.get("subCriteria");

			if(subCriterionList != null) {
				for(int subCriterionCont = 0 ; subCriterionCont < subCriterionList.size() ; subCriterionCont++) {
					Map<?, ?> subCriterionMap = (Map<?, ?>) subCriterionList.get(subCriterionCont);

					if(subCriterionMap.get("nationalEntity").equals(nationalEntity)) {
						subTenderingCriterionList.add(populateTenderingCriterion(subCriterionMap, nationalEntity));
					}
				}
			}

			tenderingCriterion.setSubTenderingCriterionList(subTenderingCriterionList);
		}
		// --- SubTenderingCriterion --- //

		tenderingCriterion.setTenderingCriterionPropertyGroupList(populateTenderingCriterionPropertiesGroups((List<Map<?, ?>>) criterionMap.get("propertiesGroups")));

		return tenderingCriterion;
	}

	private List<TenderingCriterionPropertyGroup> populateTenderingCriterionPropertiesGroups(List<Map<?, ?>> propertyGroupMapList) {
		List<TenderingCriterionPropertyGroup> tenderingCriterionPropertyGroupList = new ArrayList<>();

		if(propertyGroupMapList != null) {
			for(int propertyGroupMapCont = 0 ; propertyGroupMapCont < propertyGroupMapList.size(); propertyGroupMapCont++) {
				Map<?, ?> propertyGroupMap = propertyGroupMapList.get(propertyGroupMapCont);

				TenderingCriterionPropertyGroup tenderingCriterionPropertyGroup = populateTenderingCriterionPropertyGroup(propertyGroupMap);

				tenderingCriterionPropertyGroupList.add(tenderingCriterionPropertyGroup);
			}
		}

		return tenderingCriterionPropertyGroupList;
	}

	@SuppressWarnings("unchecked")
	private TenderingCriterionPropertyGroup populateTenderingCriterionPropertyGroup(Map<?, ?> propertyGroupMap) {
		TenderingCriterionPropertyGroup tenderingCriterionPropertyGroup = new TenderingCriterionPropertyGroup();

		// --- ID --- //
		SchemeElement id = new SchemeElement();
		id.setValue((String) propertyGroupMap.get("id"));
		id.setSchemeId("CriteriaTaxonomy");
		id.setSchemeAgencyId("EU-COM-GROW");
		id.setSchemeVersionId("2.1.1");

		tenderingCriterionPropertyGroup.setId(id);
		// --- ID --- //

		// --- PropertyGroupTypeCode --- //
		ListElement propertyGroupTypeCode = new ListElement();
		propertyGroupTypeCode.setValue((String) propertyGroupMap.get("typeCode"));
		propertyGroupTypeCode.setListId("PropertyGroupType");
		propertyGroupTypeCode.setListAgencyId("EU-COM-GROW");
		propertyGroupTypeCode.setListVersionId("2.1.1");

		tenderingCriterionPropertyGroup.setPropertyGroupTypeCode(propertyGroupTypeCode);
		// --- PropertyGroupTypeCode --- //

		// --- Properties --- //
		List<TenderingCriterionProperty> tenderingCriterionPropertyList = new ArrayList<>();

		List<Map<?, ?>> propertyMapList = (List<Map<?, ?>>) propertyGroupMap.get("properties");

		if(propertyMapList != null) {
			for(int propertyMapCont = 0 ; propertyMapCont < propertyMapList.size(); propertyMapCont++) {
				Map<?, ?> propertyMap = propertyMapList.get(propertyMapCont);

				TenderingCriterionProperty tenderingCriterionProperty = populateTenderingCriterionProperty(propertyMap);

				tenderingCriterionPropertyList.add(tenderingCriterionProperty);
			}
		}

		tenderingCriterionPropertyGroup.setTenderingCriterionPropertyList(tenderingCriterionPropertyList);
		// --- Properties --- //

		// --- SubsidiaryPropertiesGroups --- //
		tenderingCriterionPropertyGroup.setSubsidiaryTenderingCriterionPropertyGroupList(populateTenderingCriterionPropertiesGroups((List<Map<?, ?>>) propertyGroupMap.get("subsidiaryPropertiesGroups")));
		// --- SubsidiaryPropertiesGroups --- //

		return tenderingCriterionPropertyGroup;
	}

	private TenderingCriterionProperty populateTenderingCriterionProperty(Map<?, ?> propertyMap) {
		TenderingCriterionProperty tenderingCriterionProperty = new TenderingCriterionProperty();

		// --- ID --- //
		SchemeElement id = new SchemeElement();
		id.setValue((String) propertyMap.get("id"));
		id.setSchemeId("CriteriaTaxonomy");
		id.setSchemeAgencyId("EU-COM-GROW");
		id.setSchemeVersionId("2.1.1");

		tenderingCriterionProperty.setId(id);
		// --- ID --- //

		// --- Description --- //
		if(propertyMap.get("description") != null) {
			tenderingCriterionProperty.setDescription((String) propertyMap.get("description"));
		} else {
			tenderingCriterionProperty.setDescription("No description found !");
		}
		// --- Description --- //

		// --- TypeCode --- //
		ListElement typeCode = new ListElement();
		typeCode.setValue((String) propertyMap.get("typeCode"));
		typeCode.setListId("CriterionElementType");
		typeCode.setListAgencyId("EU-COM-GROW");
		typeCode.setListVersionId("2.1.1");

		tenderingCriterionProperty.setTypeCode(typeCode);
		// --- TypeCode --- //

		// --- ValueDataTypeCode --- //
		ListElement valueDataTypeCode = new ListElement();
		valueDataTypeCode.setValue((String) propertyMap.get("valueDataTypeCode"));
		valueDataTypeCode.setListId("ResponseDataType");
		valueDataTypeCode.setListAgencyId("EU-COM-GROW");
		valueDataTypeCode.setListVersionId("2.1.1");

		tenderingCriterionProperty.setValueDataTypeCode(valueDataTypeCode);
		// --- ValueDataTypeCode --- //

		return tenderingCriterionProperty;
	}
}