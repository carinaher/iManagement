package at.fh.swenga.ima.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.StudentModel;


@Repository
@Transactional
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	//public int findStudentById(int id);
	public StudentModel findStudentById(int id);
	
	//Optional<StudentModel> findOne(int id);
	
	StudentModel save(StudentModel persisted);
	
}
