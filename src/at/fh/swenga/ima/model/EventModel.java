package at.fh.swenga.ima.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Event")

public class EventModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String title;
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date start;
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date end;
	private String description;
	private String place;
	private String url;
	private String userName;
	
	public EventModel(int id, String title, Date start, Date end, String description, String place, String userName) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.description = description;
		this.place = place;
		this.userName = userName;
		this.url= generateUrl(id);
	}
	
	public String generateUrl(int id) {
		return "editEvent?id=" + id;
	}
	
	public EventModel() {
		
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return url;
	}
	public void setUrl(String url) {
		this.url = generateUrl(id);
	}


}