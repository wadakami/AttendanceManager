package jp.co.growvia.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.DailyAttendance;
import jp.co.growvia.repository.DailyAttendRepository;

@Controller
public class AttendedController {


	@Autowired
	private DailyAttendRepository dailyAttendRepository;

	@RequestMapping("/attended")
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav) {


		// true=出勤 false=退勤
		boolean attendStatus = false;
		LocalDateTime dateTime =  LocalDateTime.now();
		LocalDate date = dateTime.toLocalDate();
		DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern("HH:mm");

		DailyAttendance existUser = dailyAttendRepository.findByUsernameAndDate(loginUser.getUsername(), date);

		if (Objects.isNull(existUser)) {
			// 出勤
			attendStatus = true;
			DailyAttendance dailyattend = new DailyAttendance(loginUser.getUserAccount());
			dailyattend.setAttenddate(date);
			dailyattend.setUpdatetime(dateTime);
			dailyattend.setStarttime(dateTime.format(timeDtf));

			dailyAttendRepository.saveAttendDateTime(dailyattend);
		} else {
			// 退勤
			existUser.setEndtime(dateTime.format(timeDtf));
			existUser.setUpdatetime(dateTime);

			dailyAttendRepository.updateAttendDateTime(existUser);

		}

		mav.addObject("dateTime", dateTime);
		mav.addObject("attendStatus", attendStatus);
		mav.setViewName("attended");
		return mav;

	}

}
