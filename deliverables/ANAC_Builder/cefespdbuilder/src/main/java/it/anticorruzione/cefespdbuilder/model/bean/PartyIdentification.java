/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlElement;

public class PartyIdentification {
	private SchemeElement id;

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
}