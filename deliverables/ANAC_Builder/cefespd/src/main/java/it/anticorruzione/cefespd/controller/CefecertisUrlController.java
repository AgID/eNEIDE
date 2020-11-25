/**
 * CEF European single procurement document
 */
package it.anticorruzione.cefespd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.anticorruzione.cefespd.model.bean.Response;

@RestController
public class CefecertisUrlController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CefecertisUrlController.class.getName());

	@Value("${cefecertisUrl}")
	private String cefecertisUrl;

	@GetMapping(value = "/cefecertisUrl")
	public ResponseEntity<Response> cefecertisUrl() {
		Response response = new Response();

		try {
			response.setResult(cefecertisUrl);
			response.setSuccess(true);
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);

			response.setSuccess(false);
		}

		return ResponseEntity.ok(response);
	}
}