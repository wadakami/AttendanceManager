package jp.co.growvia.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "dailyattendance")
public class DailyAttendance {

	@Id
	@Column
	private String username;

	@Column
	private LocalDate attenddate;

	@Column
	private String starttime;

	@Column
	private String endtime;

	@Column
	private LocalDateTime updatetime;


	public DailyAttendance() {
	}

	public DailyAttendance(UserAccount user) {
		this.username = user.getUsername();
	}



}
