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
@Table(name = "t_announce")
public class Announce extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int announceId;
	
	@Column(name = "info")
	private Info info;
	
	@Column(name = "announce_target")
	private int announcetarget;
	
	 @Column(name="announce_date")
	  private Date announceDate;
	
	public int getAnnounceId() {
		return announceId;
	}

	public void setAnnounceId(int announceId) {
		this.announceId = announceId;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public int getAnnouncetarget() {
		return announcetarget;
	}

	public void setAnnouncetarget(int announcetarget) {
		this.announcetarget = announcetarget;
	}

	public Date getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(Date announceDate) {
		this.announceDate = announceDate;
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
		return this.getAnnounceId();
	}

}
