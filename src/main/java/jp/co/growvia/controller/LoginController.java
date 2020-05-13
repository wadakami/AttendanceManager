package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView viewControll(ModelAndView mav) {

		mav.addObject("iserror", false);
		mav.setViewName("login");


		return mav;

	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView viewControlla(ModelAndView mav) {

		mav.addObject("iserror", false);
		mav.setViewName("top");


		return mav;

	}

	@RequestMapping(value="/error", method=RequestMethod.POST)
	public ModelAndView viewControlls(ModelAndView mav) {

		mav.addObject("iserror", true);
		mav.setViewName("login");


		return mav;

	}

	@RequestMapping(value="/error", method=RequestMethod.GET)
	public ModelAndView viewControllss(ModelAndView mav) {

		mav.addObject("iserror", true);
		mav.setViewName("login");


		return mav;

	}

}
