package at.fh.swenga.ima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.ima.model.ForumEntryModel;

@Repository
@Transactional
public interface ForumEntryRepository extends JpaRepository<ForumEntryModel, Integer>{
	
	public ForumEntryModel findForumEntryById (int id);
	public ForumEntryModel findForumEntryByTopic (String topic);
	
	public List<ForumEntryModel> findByTopicContainsOrTextContainsOrAttachmentContainsOrUserNameContainsAllIgnoreCase(String topic, String text, String attachment, String username);

	
	ForumEntryModel save(ForumEntryModel persisted);
}
