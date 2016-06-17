package at.fh.swenga.ima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.ForumEntryModel;

@Repository
@Transactional
public interface ForumEntryRepository extends JpaRepository<ForumEntryModel, Integer>{
	
	public ForumEntryModel findForumEntryById (int id);
	
	ForumEntryModel save(ForumEntryModel persisted);
}
