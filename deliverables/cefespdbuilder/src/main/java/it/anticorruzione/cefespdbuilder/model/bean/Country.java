/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlElement;

public class Country {
	private ListElement identificationCode;

	/**
	 * @return the identificationCode
	 */
	public ListElement getIdentificationCode() {
		return identificationCode;
	}

	/**
	 * @param identificationCode the identificationCode to set
	 */
	@XmlElement(name = "IdentificationCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setIdentificationCode(ListElement identificationCode) {
		this.identificationCode = identificationCode;
	}
}