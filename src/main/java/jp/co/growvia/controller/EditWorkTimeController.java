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
import jp.co.growvia.entity.AttendSatusEnum;
import jp.co.growvia.entity.DailyAttendance;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.entity.UserLists;
import jp.co.growvia.repository.DailyAttendRepository;

@Controller
public class EditWorkTimeController {


	@Autowired
	private DailyAttendRepository dailyAttendRepository;

	@RequestMapping(value="/editWorkTime", method=RequestMethod.GET)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser,ModelAndView mav, String goAonother) {

		// プロジェクトが設定されていない場合
		if(Objects.isNull(loginUser.getUserAccount().getProjectid())) {
			if(Objects.nonNull(loginUser)) {
				mav.addObject("authirity", loginUser.getAuthority());
			}
			mav.addObject("project", true);
			mav.setViewName("top");
			return mav;
		}


		ProjectStatus project = dailyAttendRepository.findProjectById(loginUser.getUserAccount().getProjectid());

		if(Objects.isNull(project)) {
			if(Objects.nonNull(loginUser)) {
				mav.addObject("authirity", loginUser.getAuthority());
			}
			mav.addObject("project", true);
			mav.setViewName("top");

			return mav;
		}

		LocalDate localDate = LocalDate.now();
		if(Objects.nonNull(goAonother)) {
			localDate = LocalDate.parse(loginUser.getTempDate()+ "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

			if(goAonother.equals("next")) {
				localDate = localDate.plusMonths(1);
			}

			if(goAonother.equals("back")) {
				localDate = localDate.minusMonths(1);
			}

		}


		LocalDate firstDate = localDate.withDayOfMonth(1);
		int thisMonth = localDate.lengthOfMonth();
		List<DailyAttendance> userAttend = dailyAttendRepository.findAllAttend(loginUser.getUserId(),
				firstDate, localDate.withDayOfMonth(thisMonth));
		DailyAttendance[] allAttend = new DailyAttendance[thisMonth];

		for(int i =0; i< thisMonth; i++) {

			for(DailyAttendance aDay : userAttend) {

				if(aDay.getAttenddate().isEqual(firstDate.withDayOfMonth(i + 1))) {
					allAttend[i] = new DailyAttendance(aDay);

				}

			}
			if(Objects.isNull(allAttend[i])) {

				if (dailyAttendRepository.getHolidayByDate(firstDate) > 0) {
					allAttend[i] = new DailyAttendance(project, firstDate, AttendSatusEnum.祝日.name());
				} else {
					allAttend[i] = new DailyAttendance(project, firstDate);
				}

			}

			firstDate = firstDate.plusDays(1);
		}

		loginUser.setTempDate(localDate.format(DateTimeFormatter.ofPattern("yyyy/MM")));

		List<UserLists> userLists = dailyAttendRepository.getAuthorizerList();

		mav.addObject("userLists", userLists);
		mav.addObject("month", loginUser.getTempDate());
		mav.addObject("user", loginUser.getUserAccount());
		mav.addObject("projectStatus", project);
		mav.addObject("attendance", allAttend);
		mav.setViewName("editWorkTime");

		return mav;

	}

//	@RequestMapping(value="/editWorkTime/another", method=RequestMethod.POST)
//	public ModelAndView getAnotherMonth(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav, String goAonother) {
//
//
//
//
//		LocalDate firstDate = localDate.withDayOfMonth(1);
//		int thisMonth = localDate.lengthOfMonth();
//		List<DailyAttendance> userAttend = dailyAttendRepository.findAllAttend(loginUser.getUsername(),
//				firstDate, localDate.withDayOfMonth(thisMonth));
//		DailyAttendance[] allAttend = new DailyAttendance[thisMonth];
//
//		ProjectStatus project = dailyAttendRepository.findProjectByUser(loginUser.getUsername());
//
//		for(int i =0; i< thisMonth; i++) {
//
//			for(DailyAttendance aDay : userAttend) {
//
//				if(aDay.getAttenddate().isEqual(firstDate.withDayOfMonth(i + 1))) {
//					allAttend[i] = new DailyAttendance(aDay);
//
//				}
//
//			}
//			if(Objects.isNull(allAttend[i])) {
//				allAttend[i] = new DailyAttendance(project, firstDate);
//			}
//
//			firstDate = firstDate.plusDays(1);
//		}
//
//		loginUser.setTempDate(localDate.format(DateTimeFormatter.ofPattern("yyyy/MM")));
//
//
//		mav.addObject("month", loginUser.getTempDate());
//		mav.addObject("user", loginUser.getUserAccount());
//		mav.addObject("projectStatus", project);
//		mav.addObject("attendance", allAttend);
//		mav.setViewName("editWorkTime");
//
//
//		return mav;
//	}


}
