package at.fh.swenga.ima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.EventModel;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<EventModel, Integer>{
	
	public EventModel findEventById (int id);
	public List<EventModel> findByUserName(String userName);
	
	EventModel save(EventModel persisted);
}
