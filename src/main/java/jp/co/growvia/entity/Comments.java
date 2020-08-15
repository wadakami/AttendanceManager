package jp.co.growvia.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Comments {


	int requestid;

	int replyid;

	String commenter;

	String comment;

	LocalDate requestday;

	String status;

	public Comments() {

	}

	public Comments(RequestStatus requestStatus) {

		this.requestid = requestStatus.getRequestid();
		this.requestday = requestStatus.getRequestday();
		this.status = requestStatus.getStatus();

	}


}
