package jp.co.growvia.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.authentiate.LoginUser;

@RestController
public class TopController {

	@RequestMapping(value="/top", method=RequestMethod.GET)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav) {
		mav.setViewName("top");

		mav.addObject("authirity", loginUser.getAuthority());
		return mav;

	}

}
