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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.AttendSatusEnum;
import jp.co.growvia.entity.DailyAttendance;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.entity.RequestStatus;
import jp.co.growvia.entity.UserAccount;
import jp.co.growvia.repository.DailyAttendRepository;
import jp.co.growvia.repository.UserAccountRepository;

@Controller
public class AdmitController {

	@Autowired
	private DailyAttendRepository dailyAttendRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@RequestMapping(value="/admit", method=RequestMethod.GET)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, @RequestParam int id,
			ModelAndView mav, String goAonother) {

		// プロジェクトが設定されていない場合
		if(Objects.isNull(loginUser.getUserAccount().getProjectid())) {
			if(Objects.nonNull(loginUser)) {
				mav.addObject("authirity", loginUser.getAuthority());
			}
			mav.addObject("project", true);
			mav.setViewName("top");
			return mav;
		}

		RequestStatus request = dailyAttendRepository.findRequesterById(id);

		LocalDate firstDate = LocalDate.parse(request.getTargetmonth() + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		int thisMonth = firstDate.lengthOfMonth();

		UserAccount user = userAccountRepository.findByUserid(request.getUserid());

		List<DailyAttendance> userAttend = dailyAttendRepository.findAllAttend(user.getUsername(),
				firstDate, firstDate.withDayOfMonth(thisMonth));

		DailyAttendance[] allAttend = new DailyAttendance[thisMonth];

		ProjectStatus project = dailyAttendRepository.findProjectById(user.getProjectid());

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
					allAttend[i] = new DailyAttendance(project, firstDate);//要修正
				}

			}

			firstDate = firstDate.plusDays(1);
		}

		mav.addObject("user", user);
		mav.addObject("projectStatus", project);
		mav.addObject("request", request);
		mav.addObject("attendance", allAttend);
		mav.setViewName("admit");

		return mav;

	}

}
