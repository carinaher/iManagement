package at.fh.swenga.ima.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Forum")

public class ForumEntryModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String topic;
	@Column( length = 100000 )
	private String text;

	@OneToOne(cascade = CascadeType.ALL)
	private AttachmentModel attachment;



	public ForumEntryModel() {

	}

	public ForumEntryModel(String topic, String text) {
		super();
		this.topic = topic;
		this.text = text;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public AttachmentModel getAttachment() {
		return attachment;
	}

	public void setAttachment(AttachmentModel attachment) {
		this.attachment = attachment;
	}
}