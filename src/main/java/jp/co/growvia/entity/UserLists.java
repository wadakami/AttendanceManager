package jp.co.growvia.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserLists {

	private String userid;

	private String kanjiname;

	private LocalDate hiredate;

	private String authorizer;


}
