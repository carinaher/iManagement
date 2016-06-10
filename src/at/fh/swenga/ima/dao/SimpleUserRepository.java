package at.fh.swenga.ima.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import at.fh.swenga.ima.model.User;

public interface SimpleUserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByUserName(String userName);

}
