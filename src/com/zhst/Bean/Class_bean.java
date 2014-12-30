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
@Table(name="t_class")

public class Class_bean extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private int classId;
	
	@Column(name="class_name")
    private String className;
	
	@Column(name="type")
    private int type;
	
	@Column(name="path")
    private String path;
	
	@Column(name="class_year")
    private int classYear;
		
	@Column(name="valid")
	private int valid;
	
	@Column(name="create_date")
	private Date createDate;
	
	@ManyToOne
	@JoinColumn
	private User creator;
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassYear() {
		return classYear;
	}
	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}
	public String getClassname() {
		return className;
	}
	public void setClassname(String classname) {
		this.className = classname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getClassyear() {
		return classYear;
	}
	public void setClassyear(int classyear) {
		this.classYear = classyear;
	}
	
	@Override
	public int getId() {	
		return this.getClassId();
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
