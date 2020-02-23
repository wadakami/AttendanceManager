package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


	@RequestMapping(value="/login", method=RequestMethod.GET)
	private ModelAndView viewControll(ModelAndView mav) {

		mav.setViewName("login");


		return mav;

	}

}
