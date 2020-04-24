package jp.co.growvia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TopController {

	@RequestMapping(value="/top", method=RequestMethod.GET)
	private ModelAndView viewControll(ModelAndView mav) {
		System.out.println();



		mav.setViewName("top");
		return mav;

	}

}
