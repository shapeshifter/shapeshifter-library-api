// SPDX-FileCopyrightText: 2020-2021 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package eu.uftpapi.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.uftplib.service.UftpService;

@RestController
public class MessageController {

	private UftpService lib;

	public MessageController(UftpService lib) {
		this.lib = lib;
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postMessage(HttpEntity<String> httpEntity) {
		String xml = httpEntity.getBody();
		try {
			lib.sendMessage(xml);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'error': '" + e.getMessage() + "'}");
		}
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMessage(long id) {
		var message = lib.queryMessage(id);		
		if (message == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(message);
	}

}
