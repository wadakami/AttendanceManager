package jp.co.growvia.repository;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;

import jp.co.growvia.entity.DailyAttendance;

@Mapper
public interface DailyAttendRepository {
	public DailyAttendance findByUsernameAndDate(String username, LocalDate attenddate);

	public void saveAttendDateTime(DailyAttendance dailyAttendance);

	public void updateAttendDateTime(DailyAttendance dailyAttendance);

}
