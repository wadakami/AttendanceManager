package jp.co.growvia.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM");
		if(Objects.nonNull(loginUser)) {
			loginUser.setTempDate(date.format(dtf));
			mav.addObject("authirity", loginUser.getAuthority());
		}
		mav.setViewName("top");

		return mav;

	}

}
