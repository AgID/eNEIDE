/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import java.util.List;

public class EspdRequest {
	private String codiceIdentificativoGara;
	private String partyIdentification;
	private String partyName;
	private String street;
	private String city;
	private String postalZone;
	private List<String> criteriaEspd;

	/**
	 * @return the codiceIdentificativoGara
	 */
	public String getCodiceIdentificativoGara() {
		return codiceIdentificativoGara;
	}

	/**
	 * @param codiceIdentificativoGara the codiceIdentificativoGara to set
	 */
	public void setCodiceIdentificativoGara(String codiceIdentificativoGara) {
		this.codiceIdentificativoGara = codiceIdentificativoGara;
	}

	/**
	 * @return the partyIdentification
	 */
	public String getPartyIdentification() {
		return partyIdentification;
	}

	/**
	 * @param partyIdentification the partyIdentification to set
	 */
	public void setPartyIdentification(String partyIdentification) {
		this.partyIdentification = partyIdentification;
	}

	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postalZone
	 */
	public String getPostalZone() {
		return postalZone;
	}

	/**
	 * @param postalZone the postalZone to set
	 */
	public void setPostalZone(String postalZone) {
		this.postalZone = postalZone;
	}

	/**
	 * @return the criteriaEspd
	 */
	public List<String> getCriteriaEspd() {
		return criteriaEspd;
	}

	/**
	 * @param criteriaEspd the criteriaEspd to set
	 */
	public void setCriteriaEspd(List<String> criteriaEspd) {
		this.criteriaEspd = criteriaEspd;
	}
}