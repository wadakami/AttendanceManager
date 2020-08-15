package jp.co.growvia.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.AttendSatusEnum;
import jp.co.growvia.entity.DailyAttendance;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.repository.DailyAttendRepository;

@Controller
public class AttendedController {


	@Autowired
	private DailyAttendRepository dailyAttendRepository;

	@RequestMapping(value = "/attended", method = RequestMethod.POST)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav, RedirectAttributes redirectAttributes) {

		// プロジェクトが設定されていない場合
		if(Objects.isNull(loginUser.getUserAccount().getProjectid())) {
			if(Objects.nonNull(loginUser)) {
				mav.addObject("authirity", loginUser.getAuthority());
			}
			mav.addObject("project", true);
			mav.setViewName("top");
			return mav;
		}

		// true=出勤 false=退勤
		boolean attendStatus = false;
		LocalDateTime dateTime =  LocalDateTime.now();
		LocalDate date = dateTime.toLocalDate();
		DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern("HH:mm");
		String time = dateTime.format(timeDtf);

		DailyAttendance existUser = dailyAttendRepository.findByUseridAndDate(loginUser.getUserId(), date);

		if (Objects.isNull(existUser) || Objects.isNull(existUser.getUpdatetime())) {
			// 出勤
			attendStatus = true;
			DailyAttendance dailyattend = new DailyAttendance();
			dailyattend.setUserid(loginUser.getUserId());
			dailyattend.setAttenddate(date);
			dailyattend.setUpdatetime(dateTime);
			dailyattend.setStarttime(time);
			dailyattend.setEndtime(null);
			dailyattend.setStatus(AttendSatusEnum.出勤.toString());

			dailyAttendRepository.saveAttendDateTime(dailyattend);
		} else {

			// 退勤しているにもかかわらずボタンが押された場合
			if( Objects.nonNull(existUser.getEndtime())) {
				if(Objects.nonNull(loginUser)) {
					mav.addObject("authirity", loginUser.getAuthority());
				}
				mav.addObject("alart", true);
				mav.setViewName("top");
				return mav;
			}

			ProjectStatus project = dailyAttendRepository.findProjectById(loginUser.getUserAccount().getProjectid());

			String restTime = "01:00";
			if (Objects.nonNull(project)) {
				restTime = project.getResttime();
			}

			// 退勤
			existUser.setEndtime(time);
			existUser.setResttime(restTime);
			existUser.setUpdatetime(dateTime);

			dailyAttendRepository.updateAttendDateTime(existUser);

		}

		redirectAttributes.addFlashAttribute("dateTime", date + " " + time);
		redirectAttributes.addFlashAttribute("attendStatus", attendStatus);
		mav.setViewName("redirect:attended");
		return mav;

	}

	@RequestMapping(value = "/attended", method = RequestMethod.GET)
	public ModelAndView show(ModelAndView mav,
			@ModelAttribute("attendStatus") String attendStatus) {

		if (attendStatus.isEmpty()) {
			mav.setViewName("top");
		} else {
			mav.setViewName("attended");
		}

		return mav;

	}


}
