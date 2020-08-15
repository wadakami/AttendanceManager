package jp.co.growvia.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyAttend implements Serializable {

	private String date;

	private String status;

	private String start;

	private String end;

	private String rest;

}
