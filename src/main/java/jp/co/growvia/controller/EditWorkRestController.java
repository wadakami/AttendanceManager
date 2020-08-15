package jp.co.growvia.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.Comments;
import jp.co.growvia.entity.MailContainer;
import jp.co.growvia.entity.MonthlyAttend;
import jp.co.growvia.entity.RequestStatus;
import jp.co.growvia.entity.RequestStatusEnum;
import jp.co.growvia.repository.DailyAttendRepository;
import jp.co.growvia.service.EditWorkService;

@RestController
public class EditWorkRestController {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private EditWorkService editWorkService;

	@Autowired
	DailyAttendRepository dailyAttendRepository;

	@RequestMapping(value="/api/sendRequest", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> sendEmail(@AuthenticationPrincipal LoginUser loginUser,@RequestBody  MailContainer content){


		RequestStatus requestStatus = new RequestStatus(loginUser.getUserAccount());

		requestStatus.setTargetmonth(loginUser.getTempDate());
		requestStatus.setRequestday(LocalDate.now());
		requestStatus.setStatus(RequestStatusEnum.承認待ち.name());

		dailyAttendRepository.saveRequestDay(requestStatus);

		Comments comment = new Comments(requestStatus);
		comment.setComment(content.getComment());
		comment.setCommenter(loginUser.getUserId());

		dailyAttendRepository.saveComment(comment);

		SimpleMailMessage msg = new SimpleMailMessage();
		// 宛先を詰める
		msg.setTo(dailyAttendRepository.getEmail(content.getAuthorizer1()));

		msg.setFrom("wadaue.k@growvia.co.jp");
		//タイトル未定
		msg.setSubject(editWorkService.buildTitle(loginUser));

		//内容詰める（html形式がいいけどとりあえずテキスト形式で）
		msg.setText(content.getComment() + System.lineSeparator() + "https://kintai.growvia.co.jp/admit?id=" + requestStatus.getRequestid());
		mailSender.send(msg);


		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value="/api/regist", method=RequestMethod.POST)
	@ResponseBody
	public String registAllData(@AuthenticationPrincipal LoginUser loginUser,@RequestBody List<MonthlyAttend> month){

		for(MonthlyAttend attend : month) {
			attend.setDate(loginUser.getTempDate() + "/" +  attend.getDate());
		}


		dailyAttendRepository.saveMonthUtilToday(loginUser.getUserId(), month);

		return null;

	}



}
