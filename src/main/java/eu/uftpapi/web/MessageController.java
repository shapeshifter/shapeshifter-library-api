package eu.uftpapi.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
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

	@RequestMapping(value = "/message", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String postMessage(HttpEntity<String> httpEntity) {
		String xml = httpEntity.getBody();
		System.out.println(xml);
		lib.sendMessage(xml);
		return "OK";
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMessage(long id) {
		System.out.println(id);
        return "OK";
	}

}
