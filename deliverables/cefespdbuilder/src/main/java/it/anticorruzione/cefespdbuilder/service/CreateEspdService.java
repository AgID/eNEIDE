/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.service;

import it.anticorruzione.cefespdbuilder.model.bean.EspdRequest;
import it.anticorruzione.cefespdbuilder.model.bean.QualificationApplicationRequest;

public interface CreateEspdService {
	public QualificationApplicationRequest createEspd(EspdRequest espdRequest, String nationalEntity);
}