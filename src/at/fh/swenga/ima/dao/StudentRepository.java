package at.fh.swenga.ima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.ima.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	

}
