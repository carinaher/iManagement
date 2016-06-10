package at.fh.swenga.ima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>  {

	public User findByUserName(String userName);
	public User save(User persisted);
	
	

}
