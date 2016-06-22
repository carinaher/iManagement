package at.fh.swenga.ima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.UserRole;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>  {

	public UserRole findByUserRoleId(int id);
	public UserRole save(UserRole persisted);
	
	public UserRole findByRole(String role);
	public List<UserRole> findByUserUserName(String userName);
	

}
