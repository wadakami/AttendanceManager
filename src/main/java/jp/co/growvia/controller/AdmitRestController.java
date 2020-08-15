package jp.co.growvia.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.growvia.authentiate.LoginUser;
import jp.co.growvia.entity.AttendSatusEnum;
import jp.co.growvia.entity.Comments;
import jp.co.growvia.entity.DailyAttendance;
import jp.co.growvia.entity.MailContainer;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.entity.RequestStatus;
import jp.co.growvia.entity.RequestStatusEnum;
import jp.co.growvia.entity.UserAccount;
import jp.co.growvia.repository.DailyAttendRepository;
import jp.co.growvia.repository.UserAccountRepository;

@RestController
public class AdmitRestController {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private DailyAttendRepository dailyAttendRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@RequestMapping(value="/api/admitRequest", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> sendEmail(@AuthenticationPrincipal LoginUser loginUser,@RequestBody  MailContainer content){

		RequestStatus request = dailyAttendRepository.findRequesterById(content.getId());

		if (Objects.equals(content.getStatus(), "承認")) {
			request.setStatus(RequestStatusEnum.承認済み.name());
		} else {
			request.setStatus(RequestStatusEnum.否承認.name());
		}
		Comments comment = new Comments(request);
		comment.setComment(content.getComment());
		comment.setCommenter(loginUser.getUserId());

		dailyAttendRepository.updateRequestStatus(request);
		dailyAttendRepository.saveComment(comment);

		SimpleMailMessage msg = new SimpleMailMessage();
		// 宛先を詰める
		msg.setTo(dailyAttendRepository.getEmail(content.getRequester()));

		msg.setFrom("wadaue.k@growvia.co.jp");
		//タイトル未定
		msg.setSubject(request.getStatus() + ":" + request.getRequestday() + "申請分勤怠");

		//内容詰める（html形式がいいけどとりあえずテキスト形式で）
		msg.setText(loginUser.getUserAccount().getKanjiname() + "さんがリクエストを「" + request.getStatus() + "」にしました。"
				+ System.lineSeparator() + content.getComment());
		mailSender.send(msg);

		//

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value="/api/getAttendance", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getList(@AuthenticationPrincipal LoginUser loginUser, @RequestParam String type,
			@RequestParam String month, @RequestParam int id) {

		LocalDate localDate = LocalDate.parse(month + "/01", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		if(type.equals("forward")) {
			localDate = localDate.plusMonths(1);
		}

		if(type.equals("back")) {
			localDate = localDate.minusMonths(1);
		}

		int thisMonth = localDate.lengthOfMonth();


		RequestStatus request = dailyAttendRepository.findRequesterById(id);

		UserAccount user = userAccountRepository.findByUserid(request.getUserid());

		List<DailyAttendance> userAttend = dailyAttendRepository.findAllAttend(user.getUsername(),
				localDate, localDate.withDayOfMonth(thisMonth));
		DailyAttendance[] allAttend = new DailyAttendance[thisMonth];

		ProjectStatus project = dailyAttendRepository.findProjectById(user.getProjectid());

		for(int i =0; i< thisMonth; i++) {

			for(DailyAttendance aDay : userAttend) {

				if(aDay.getAttenddate().isEqual(localDate.withDayOfMonth(i + 1))) {
					allAttend[i] = new DailyAttendance(aDay);

				}

			}
			if(Objects.isNull(allAttend[i])) {

				if (dailyAttendRepository.getHolidayByDate(localDate) > 0) {
					allAttend[i] = new DailyAttendance(project, localDate, AttendSatusEnum.祝日.name());
				} else {
					allAttend[i] = new DailyAttendance(project, localDate);
				}

			}

			localDate = localDate.plusDays(1);
		}

		return new ResponseEntity<>(allAttend, HttpStatus.OK);

	}


}
