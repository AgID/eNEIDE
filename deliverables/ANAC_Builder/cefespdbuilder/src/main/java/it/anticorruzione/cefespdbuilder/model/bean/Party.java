/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"partyIdentification", "partyName", "postalAddress"})
public class Party {
	private PartyIdentification partyIdentification;
	private PartyName partyName;
	private PostalAddress postalAddress;

	/**
	 * @return the partyIdentification
	 */
	public PartyIdentification getPartyIdentification() {
		return partyIdentification;
	}

	/**
	 * @param partyIdentification the partyIdentification to set
	 */
	@XmlElement(name = "PartyIdentification", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setPartyIdentification(PartyIdentification partyIdentification) {
		this.partyIdentification = partyIdentification;
	}

	/**
	 * @return the partyName
	 */
	public PartyName getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	@XmlElement(name = "PartyName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setPartyName(PartyName partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the postalAddress
	 */
	public PostalAddress getPostalAddress() {
		return postalAddress;
	}

	/**
	 * @param postalAddress the postalAddress to set
	 */
	@XmlElement(name = "PostalAddress", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}
}