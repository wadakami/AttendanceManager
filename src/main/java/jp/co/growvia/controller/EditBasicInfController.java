/**
 *
 */
package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Midori
 *
 */
@Controller
public class EditBasicInfController {
	@RequestMapping(value="/editBasicInf", method=RequestMethod.GET)
	private ModelAndView viewControll(ModelAndView mav) {

		mav.setViewName("editBasicInf");


		return mav;

	}


}
