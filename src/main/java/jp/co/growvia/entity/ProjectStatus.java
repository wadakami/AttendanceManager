package jp.co.growvia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "projectstatus")
public class ProjectStatus {

	@Id
	@Column
	private int projectid;

	@Column
	private String projectname;

	@Column
	private String starttime;

	@Column
	private String endtime;

	@Column
	private String resttime;

}
