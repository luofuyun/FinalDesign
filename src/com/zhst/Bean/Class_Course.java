package com.zhst.Bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_class_course")
public class Class_Course extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int classCourseId;

	@ManyToOne
	@JoinColumn
	private Class_bean courseClass;
	
	@OneToOne
	@JoinColumn
	private User teacher;

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	@ManyToOne
	@JoinColumn
	private Course course;

	@Column(name = "type")
	private int type;

	@Column(name = "isclose")
	private int isclose;
	
	public int getIsclose() {
		return isclose;
	}

	public void setIsclose(int isclose) {
		this.isclose = isclose;
	}

	@Column(name="valid")
    private int valid;
    
    @Column(name="create_date")
    private Date createDate;
    
	public int getClassCourseId() {
		return classCourseId;
	}

	public void setClassCourseId(int classCourseId) {
		this.classCourseId = classCourseId;
	}

	
	public Class_bean getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(Class_bean courseClass) {
		this.courseClass = courseClass;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int getId() {
		return this.getClassCourseId();
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
