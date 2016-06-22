package at.fh.swenga.ima.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@NamedQueries(
		{
@NamedQuery(name = "TaskModel.findAllTasksForUser", 
query = "SELECT t FROM TaskModel t WHERE "
		+ "t.userName IS :loggedInUser AND "
		+ "t.title LIKE :searchString  OR "
		+ "t.description LIKE :searchString  OR "
		+ "t.place LIKE :searchString  "
		)
		
	
		})

@Entity
@Table(name = "Task")

public class TaskModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String title;

	private String description;

	private Boolean status;

	@NotNull(message = "{0} is required")
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	@Future(message = "{0} must be in the future")
	private Date start;

	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	@Future(message = "{0} must be in the future")
	private Date end;

	private String place;

	@NotNull(message = "{0} is required")
	private String userName;
	
	// url for editing is automatically generated
	private String url;

	public TaskModel(String title, String description, Boolean status, Date start, Date end, String place,
			String userName) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.start = start;
		this.end = end;
		this.place = place;
		this.userName = userName;
		this.url = generateUrl(id);
	}

	public TaskModel() {

	}

	// helper methods
	public String generateUrl(int id) {
		return "editTask?id=" + id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return generateUrl(id);
	}
	
	public void setUrl(String url) {
		this.url = generateUrl(id);
	}
	
	

}