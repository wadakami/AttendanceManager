/**
 *
 */
package jp.co.growvia.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.repository.DailyAttendRepository;

/**
 * @author Midori
 *
 */
@Controller
public class EditBasicInfController {

	@Autowired
	private DailyAttendRepository dailyAttendRepository;

	@RequestMapping(value="/editBasicInf", method=RequestMethod.GET)
	public ModelAndView viewControll(@AuthenticationPrincipal LoginUser loginUser, ModelAndView mav) {

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

		mav.addObject("project", project);
		mav.setViewName("editBasicInf");

		return mav;

	}


}
