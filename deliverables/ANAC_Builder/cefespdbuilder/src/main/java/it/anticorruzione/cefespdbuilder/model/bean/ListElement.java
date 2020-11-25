/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ListElement {
	private String value;
	private String listId;
	private String listAgencyId;
	private String listVersionId;

	/**
	 * @return the listId
	 */
	public String getListId() {
		return listId;
	}

	/**
	 * @return the value
	 */
	@XmlValue()
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param listId the listId to set
	 */
	@XmlAttribute(name = "listID")
	public void setListId(String listId) {
		this.listId = listId;
	}

	/**
	 * @return the listAgencyId
	 */
	public String getListAgencyId() {
		return listAgencyId;
	}

	/**
	 * @param listAgencyId the listAgencyId to set
	 */
	@XmlAttribute(name = "listAgencyID")
	public void setListAgencyId(String listAgencyId) {
		this.listAgencyId = listAgencyId;
	}

	/**
	 * @return the listVersionId
	 */
	public String getListVersionId() {
		return listVersionId;
	}

	/**
	 * @param listVersionId the listVersionId to set
	 */
	@XmlAttribute(name = "listVersionID")
	public void setListVersionId(String listVersionId) {
		this.listVersionId = listVersionId;
	}
}