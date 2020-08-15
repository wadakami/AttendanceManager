package jp.co.growvia.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.growvia.entity.Comments;
import jp.co.growvia.entity.DailyAttendance;
import jp.co.growvia.entity.Holiday;
import jp.co.growvia.entity.MonthlyAttend;
import jp.co.growvia.entity.ProjectStatus;
import jp.co.growvia.entity.RequestStatus;
import jp.co.growvia.entity.UserLists;

@Mapper
public interface DailyAttendRepository {

	public DailyAttendance findByUseridAndDate(String userid, LocalDate attenddate);

	public ProjectStatus findProjectById(int projectid);

	public List<DailyAttendance> findAllAttend(String userid, LocalDate firstday, LocalDate lastday);

	public void saveAttendDateTime(DailyAttendance dailyAttendance);

	public void updateAttendDateTime(DailyAttendance dailyAttendance);

	//使わなくなる予定
	public void saveProject(ProjectStatus project);

	//使わなくなる予定
	public void updateProject(ProjectStatus project);

	public void saveMonthUtilToday(@Param("userid")String userid, @Param("month")List<MonthlyAttend> lastday);

	public int saveRequestDay(RequestStatus requestStatus);

	public void saveComment(Comments comment);

	public void updateRequestStatus(RequestStatus requestStatus);

	//要修正
	public List<RequestStatus> getAllRequester(String authorizer, LocalDate limitday);

	public RequestStatus findRequesterById(int requestid);

	//要修正？
	public List<UserLists> getUserList();

	public List<UserLists> getAuthorizerList();

	public List<Holiday> getAllHoliday(int yare);

	public int getHolidayByDate(LocalDate date);

	public String getEmail(String userid);

}
