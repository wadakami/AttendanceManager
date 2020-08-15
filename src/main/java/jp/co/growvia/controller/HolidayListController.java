package jp.co.growvia.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.Holiday;
import jp.co.growvia.repository.DailyAttendRepository;

@Controller
public class HolidayListController {

    public static final String URL = "https://holidays-jp.github.io/api/v1/date.json";

	@Autowired
	DailyAttendRepository dailyAttendRepository;


	@RequestMapping(value="/holidayList", method=RequestMethod.GET)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav,String passYaer, String goAonother) {

		LocalDate year = LocalDate.now();
		if(Objects.nonNull(goAonother)) {
			year = LocalDate.parse(passYaer + "/01/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

			if(goAonother.equals("next")) {
				year = year.plusYears(1);
			}

			if(goAonother.equals("back")) {
				year = year.minusYears(1);
			}

		}
		List<Holiday> holiday = dailyAttendRepository.getAllHoliday(year.getYear());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
		String none = null;
		if(!holiday.isEmpty()) {
			for(Holiday day : holiday) {
				day.setDayAndMonth(day.getDay().format(dtf));
				day.setDayOfWeek(day.getDay());
			}

		} else {
			none = "この年の祝日は取得していません。";
		}


		mav.addObject("none", none);
		mav.addObject("yaer", year.getYear());
		mav.addObject("holiday", holiday);
		mav.setViewName("holidayList");


		return mav;

	}

}
