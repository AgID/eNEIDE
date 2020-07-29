/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.model.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"streetName", "cityName", "postalZone", "country"})
public class PostalAddress {
	private String streetName;
	private String cityName;
	private String postalZone;
	private Country country;

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	@XmlElement(name = "StreetName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	@XmlElement(name = "CityName", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	@XmlElement(name = "PostalZone", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
	public void setPostalZone(String postalZone) {
		this.postalZone = postalZone;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	@XmlElement(name = "Country", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
	public void setCountry(Country country) {
		this.country = country;
	}
}