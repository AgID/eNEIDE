/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.anticorruzione.cefespdbuilder.model.bean.EspdRequest;
import it.anticorruzione.cefespdbuilder.model.bean.QualificationApplicationRequest;
import it.anticorruzione.cefespdbuilder.service.CreateEspdService;

@CrossOrigin(origins = "*")
@RestController
public class CreateEspdController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateEspdController.class.getName());

	@Autowired
	private CreateEspdService createEspdService;

	@PostMapping(value = "/api/v1/createEspd", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<QualificationApplicationRequest> createEspd(@RequestBody EspdRequest espdRequest, @RequestParam(required = false, value = "nationalEntity") String nationalEntity) {
		try {
			QualificationApplicationRequest qualificationApplicationRequest = createEspdService.createEspd(espdRequest, nationalEntity);

			return ResponseEntity.ok(qualificationApplicationRequest);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}