package at.fh.swenga.ima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Student")

public class StudentModel implements java.io.Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	String userName; //fhuser
	String firstName;
	String lastName;
	String githubUser;
	String eMail;
	@Column(nullable = true)
	int year;
	@Column(nullable = true)
	int groupId;
	

	
	//Passwort kommt in eigenes Model "User" -> für Anmeldung
	
	//Picture???
	
	public StudentModel(){
		
	}
	
	public StudentModel(String userName, String firstName, String lastName, String githubUser, String eMail,int year, int groupId) {

		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.githubUser = githubUser;
		this.eMail = eMail;
		this.year = year;
		this.groupId = groupId;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getGithubUser() {
		return githubUser;
	}



	public void setGithubUser(String githubUser) {
		this.githubUser = githubUser;
	}



	public String geteMail() {
		return eMail;
	}



	public void seteMail(String eMail) {
		this.eMail = eMail;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public int getGroupId() {
		return groupId;
	}



	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	
	
	
	
	
	
	

}
