package at.fh.swenga.ima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.TaskModel;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {

	public TaskModel findTaskById(int id);

	@SuppressWarnings("unchecked")
	TaskModel save(TaskModel persisted);

	public List<TaskModel> findByTaskName(String name);

	public List<TaskModel> findByDescription(String name);

	public List<TaskModel> findByStatus(Boolean TRUE);
}
