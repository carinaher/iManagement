package at.fh.swenga.ima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.StudentModel;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	public StudentModel findById(int id);

	@SuppressWarnings("unchecked")
	public StudentModel save(StudentModel persisted);
	
	public List<StudentModel> findByUserName(String name);
	
	public List<StudentModel> findByFirstName(String name);
	
	public List<StudentModel> findByLastName(String name);

	public List<StudentModel> findByGithubUser(String name);

	public List<StudentModel> findByGroupId(int ID);

	public StudentModel findFirstByUserName(String userName);
}
