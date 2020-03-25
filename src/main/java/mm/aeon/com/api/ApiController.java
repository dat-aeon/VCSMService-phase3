package mm.aeon.com.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "API is started....";
	}
}
