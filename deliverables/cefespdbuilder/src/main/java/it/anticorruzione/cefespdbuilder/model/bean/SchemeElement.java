/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class SchemeElement {
	private String value;
	private String schemeId;
	private String schemeAgencyId;
	private String schemeVersionId;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	@XmlValue()
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the schemeId
	 */
	public String getSchemeId() {
		return schemeId;
	}

	/**
	 * @param schemeId the schemeId to set
	 */
	@XmlAttribute(name = "schemeID")
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	/**
	 * @return the schemeAgencyId
	 */
	public String getSchemeAgencyId() {
		return schemeAgencyId;
	}

	/**
	 * @param schemeAgencyId the schemeAgencyId to set
	 */
	@XmlAttribute(name = "schemeAgencyID")
	public void setSchemeAgencyId(String schemeAgencyId) {
		this.schemeAgencyId = schemeAgencyId;
	}

	/**
	 * @return the schemeVersionId
	 */
	public String getSchemeVersionId() {
		return schemeVersionId;
	}

	/**
	 * @param schemeVersionId the schemeVersionId to set
	 */
	@XmlAttribute(name = "schemeVersionID")
	public void setSchemeVersionId(String schemeVersionId) {
		this.schemeVersionId = schemeVersionId;
	}
}