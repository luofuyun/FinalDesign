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
@Table(name = "t_submit")
public class Submit extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int submitId;

	@ManyToOne
	@JoinColumn
	private Class_bean submitClass;

	@ManyToOne
	@JoinColumn
	private Arrange arrange;

	@ManyToOne
	@JoinColumn
	private User student;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "answer",length=2000)
	private String answer;

	@Column(name = "number")
	private int number;

	@Column(name = "score")
	private double score;

	@Column(name = "score_time")
	private Date scoreTime;

	@Column(name = "remark")
	private String remark;

	@Column(name = "submit_time")
	private Date submitTime;
	
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	@Column(name = "method")
	private int method;

	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="valid")
	private int valid;
	public int getSubmitId() {
		return submitId;
	}

	public void setSubmitId(int submitId) {
		this.submitId = submitId;
	}

	
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	
	
	public Class_bean getSubmitClass() {
		return submitClass;
	}

	public void setSubmitClass(Class_bean submitClass) {
		this.submitClass = submitClass;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	

	public Date getScoreTime() {
		return scoreTime;
	}

	public void setScoreTime(Date scoreTime) {
		this.scoreTime = scoreTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public void setArrange(Arrange arrange) {
		this.arrange = arrange;
	}

	public Arrange getArrange() {
		return arrange;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getValid() {
		return valid;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getScore() {
		return score;
	}

}
