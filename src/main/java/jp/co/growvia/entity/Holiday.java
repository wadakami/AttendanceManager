package jp.co.growvia.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Holiday {

	LocalDate day;

	String jpday;

	//TBLカラムにはなし
	String dayOfWeek;

	//TBLカラムにはなし
	String dayAndMonth;

	public void setDayOfWeek(LocalDate date) {
		int day = date.getDayOfWeek().getValue();
		if(day == 1) {
			this.dayOfWeek = "月";
		}
		if(day == 2) {
			this.dayOfWeek = "火";
		}
		if(day == 3) {
			this.dayOfWeek = "水";
		}
		if(day == 4) {
			this.dayOfWeek = "木";
		}
		if(day == 5) {
			this.dayOfWeek = "金";
		}
		if(day == 6) {
			this.dayOfWeek = "土";
		}
		if(day == 7) {
			this.dayOfWeek = "日";
		}
	}

}
