package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequesterListController {

	@RequestMapping(value="/requesterList", method=RequestMethod.GET)
	public ModelAndView viewControll(ModelAndView mav) {

		mav.setViewName("requesterList");

		return mav;

	}

}
