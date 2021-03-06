package at.fh.swenga.ima.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "user_roles")
public class UserRole implements java.io.Serializable {
	
	@Id
	@Column(name = "user_role_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRoleId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false) // save username in DB table
	private User user;
	@Column(name = "role", nullable = false, length = 45)
	private String role;
	

	public UserRole() {};
	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}
	
	
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
