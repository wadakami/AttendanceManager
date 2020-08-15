package jp.co.growvia.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailContainer implements Serializable {

	private int id;

	private String requester;

	private String comment;

	private String authorizer1;

	private String authorizer2;

	private String status;

}
