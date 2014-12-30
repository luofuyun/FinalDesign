package com.zhst.Bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_arrange")
public class Arrange extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int arrangeId;

	@ManyToOne
	@JoinColumn
	private Item item;

	@ManyToOne
	@JoinColumn
	private Class_Course classCourse;

	@Column(name = "doc_path")
	private String docpath;

	@Column(name = "start_Date")
	private Date startTime;

	@Column(name = "deadline_date")
	private Date deadlineDate;

	@Column(name = "is_default")
	private int isDefault;

	@Column(name = "has_submit")
	private int hasSubmit;

	@Column(name = "submit_time")
	private String submitTime;

	@Column(name = "score")
	private String score;

	@Column(name = "has_passtime")
	private int hasPasstime;

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getHasPasstime() {
		return hasPasstime;
	}

	public void setHasPasstime(int hasPasstime) {
		this.hasPasstime = hasPasstime;
	}

	@ManyToOne
	@JoinColumn
	private User creater;

	@Column(name = "valid")
	private int valid;

	@Column(name = "create_date")
	private Date createDate;

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getArrangeId() {
		return arrangeId;
	}

	public void setArrangeId(int arrangeId) {
		this.arrangeId = arrangeId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Class_Course getClassCourse() {
		return classCourse;
	}

	public void setClassCourse(Class_Course classCourse) {
		this.classCourse = classCourse;
	}

	public String getDocpath() {
		return docpath;
	}

	public void setDocpath(String docpath) {
		this.docpath = docpath;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public int getHasSubmit() {
		return hasSubmit;
	}

	public void setHasSubmit(int hasSubmit) {
		this.hasSubmit = hasSubmit;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
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

}
