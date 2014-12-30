package com.zhst.Bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_read_info")
public class ReadInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int readInfoId;
	
	@Column(name = "announce")
	private Announce announce;
	
	@Column(name = "reader")
	private User reader;
	
	@Column(name="read_date")
	private Date readDate;
	
	public int getReadInfoId() {
		return readInfoId;
	}

	public void setReadInfoId(int readInfoId) {
		this.readInfoId = readInfoId;
	}

	public Announce getAnnounce() {
		return announce;
	}

	public void setAnnounce(Announce announce) {
		this.announce = announce;
	}

	public User getReader() {
		return reader;
	}

	public void setReader(User reader) {
		this.reader = reader;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="valid")
    private int valid;
    
    @Column(name="create_date")
    private Date createDate;
	
	@Override
	public int getId() {
		return this.getReadInfoId();
	}

}
