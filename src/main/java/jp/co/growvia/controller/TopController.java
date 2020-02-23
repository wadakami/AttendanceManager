package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopController {

	@RequestMapping("/top")
	private ModelAndView viewControll(ModelAndView mav) {
		System.out.println();



		mav.setViewName("top");
		return mav;

	}

}
