package jp.co.growvia.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RequestStatus {

	int requestid;

	String userid;

	String authorizer;

	LocalDate requestday;

	String status;

	String targetmonth;

	// TBLにはない
	String username;

	public RequestStatus() {

	}


	public RequestStatus(UserAccount user) {
		this.userid = user.getUserid();
		this.authorizer = user.getAuthorizer();

	}


}
