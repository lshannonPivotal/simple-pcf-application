/**
 *
 */
package io.pivotal.fe.pcf.sample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Simple Controller for testing to ensure an end point works
 * @author lshannon
 *
 */
@Controller
public class BasicController {

	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String helloMeetUp() {
        return "Hello Phil - delivered from Jenkins! Thanks Aron, Royston!";
    }

}
