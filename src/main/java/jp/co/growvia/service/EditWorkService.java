package jp.co.growvia.service;

import org.springframework.stereotype.Service;

import jp.co.growvia.authentiate.LoginUser;

@Service
public class EditWorkService {


	public String buildTitle(LoginUser user) {

		String title = "承認要求: 勤怠状況_" + user.getUserAccount().getKanjiname() + "_" + user.getTempDate();

		return title;
	}


}
