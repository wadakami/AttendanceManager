package jp.co.growvia.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.repository.DailyAttendRepository;

@RestController
public class EditBasicRestController {

	@Autowired
	private DailyAttendRepository dailyAttendRepository;


	@RequestMapping(value="/api/changeInfo", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> sendEmail(@AuthenticationPrincipal LoginUser loginUser,@RequestBody  ProjectStatus project){

		// UserAccountのprojectidだけupdateするよう修正。同じIDならスルーして返却
		ProjectStatus existProject = dailyAttendRepository.findProjectById(loginUser.getUserAccount().getProjectid());

		if(Objects.isNull(existProject)) {
			dailyAttendRepository.saveProject(project);
		} else {
			dailyAttendRepository.updateProject(project);
		}

		return new ResponseEntity<>(project, HttpStatus.OK);

	}
}
