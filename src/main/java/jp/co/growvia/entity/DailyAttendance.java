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
	private String userid;

	@Column
	private LocalDate attenddate;

	@Column
	private String starttime;

	@Column
	private String endtime;

	@Column
	private LocalDateTime updatetime;

	@Column
	private String status;

	@Column
	private String resttime;

	// 曜日をはめ込む用（TBLにはなし）
	private String day;

	public DailyAttendance() {
	}

	public DailyAttendance(DailyAttendance aDay) {
		this.userid = aDay.getUserid();
		this.attenddate = aDay.getAttenddate();
		this.starttime = aDay.getStarttime();
		this.endtime = aDay.getEndtime();
		this.status = aDay.getStatus();
		this.resttime = aDay.getResttime();
		this.day = getJpDay(aDay.getAttenddate());
	}

	public DailyAttendance(ProjectStatus tempData, LocalDate date) {

		this.attenddate = date;
		this.status = getDayOfWeek(date);
		this.day = getJpDay(date);
		if (this.status.equals("休日")) {
			this.starttime = "-";
			this.endtime = "-";
			this.resttime = "-";

		} else {
			this.starttime = tempData.getStarttime();
			this.endtime = tempData.getEndtime();
			this.resttime = tempData.getResttime();

		}
	}

	public DailyAttendance(ProjectStatus tempData, LocalDate date, String holiday) {

		this.attenddate = date;
		this.status = holiday;
		this.day = getJpDay(date);
		this.starttime = "-";
		this.endtime = "-";
		this.resttime = "-";

	}


	public String getJpDay(LocalDate date) {
		int day = date.getDayOfWeek().getValue();
		if(day == 1) {
			return "月";
		}
		if(day == 2) {
			return "火";
		}
		if(day == 3) {
			return "水";
		}
		if(day == 4) {
			return "木";
		}
		if(day == 5) {
			return "金";
		}
		if(day == 6) {
			return "土";
		}
		if(day == 7) {
			return "日";
		}
		return null;
	}

	public String getDayOfWeek(LocalDate date) {

		int day = date.getDayOfWeek().getValue();
		if(day == 6 || day == 7) {
			return AttendSatusEnum.休日.name();
		}

		return AttendSatusEnum.出勤.name();
	}




}
