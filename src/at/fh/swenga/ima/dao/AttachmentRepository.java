package at.fh.swenga.ima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.ima.model.AttachmentModel;

public interface AttachmentRepository extends JpaRepository<AttachmentModel, Integer> {

	
	
}
