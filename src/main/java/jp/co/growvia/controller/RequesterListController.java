package jp.co.growvia.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.RequestStatus;
import jp.co.growvia.repository.DailyAttendRepository;

@Controller
public class RequesterListController {

	@Autowired
	DailyAttendRepository dailyAttendRepository;


	@RequestMapping(value="/requesterList", method=RequestMethod.GET)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav) {


		LocalDate localDate = LocalDate.now();
		localDate = localDate.minusDays(7);
		System.out.println(localDate);
		List<RequestStatus> requester = dailyAttendRepository.getAllRequester(loginUser.getUserAccount().getUserid(), localDate);

		mav.addObject("requesters", requester);
		mav.setViewName("requesterList");

		return mav;

	}

}
