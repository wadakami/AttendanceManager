package jp.co.growvia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.entity.UserLists;
import jp.co.growvia.repository.DailyAttendRepository;

@Controller
public class UserListController {

	@Autowired
	DailyAttendRepository dailyAttendRepository;

	@RequestMapping(value="/userList", method=RequestMethod.GET)
	public ModelAndView viewControll(ModelAndView mav) {


		List<UserLists> users = dailyAttendRepository.getUserList();

		mav.addObject("users", users);
		mav.setViewName("userList");


		return mav;

	}

}
