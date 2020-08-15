package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ModelAndView viewControlla(ModelAndView mav) {

		mav.addObject("iserror", false);
		mav.setViewName("top");

		System.out.println("b");

		return mav;

	}



}
