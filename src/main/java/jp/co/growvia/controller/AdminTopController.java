package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminTopController {

	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView viewControll(ModelAndView mav) {

		mav.setViewName("adminTop");

		return mav;

	}

}
