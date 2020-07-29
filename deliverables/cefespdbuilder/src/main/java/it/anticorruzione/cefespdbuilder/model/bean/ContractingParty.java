/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"party"})
public class ContractingParty {
	private Party party;

	/**
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * @param party the party to set
	 */
	@XmlElement(name = "Party", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setParty(Party party) {
		this.party = party;
	}
}