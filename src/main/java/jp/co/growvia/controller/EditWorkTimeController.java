package jp.co.growvia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditWorkTimeController {
	@RequestMapping(value="/editWorkTime", method=RequestMethod.GET)
	private ModelAndView viewControll(ModelAndView mav) {

		mav.setViewName("editWorkTime");


		return mav;

	}

}
