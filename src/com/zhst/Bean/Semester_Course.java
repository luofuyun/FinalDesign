package com.zhst.Bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="t_semester_course")

	public class Semester_Course extends BaseEntity{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int semesterCourseId;
		
		@ManyToOne
		@JoinColumn
		private Semester semester;
		
		@ManyToOne
		@JoinColumn
		private Course course;
		
		@ManyToOne
		@JoinColumn
		private User teacher;
			
		@Column(name="topic")
		private String topic;
		
		@Column(name="isClose")
		private int isClose;
		
		public int getIsClose() {
			return isClose;
		}
		public void setIsClose(int isClose) {
			this.isClose = isClose;
		}
		@Column(name="valid")
	    private int valid;
	    
	    @Column(name="create_date")
	    private Date createDate;
		public int getSemesterCourseId() {
			return semesterCourseId;
		}
		public void setSemesterCourseId(int semesterCourseId) {
			this.semesterCourseId = semesterCourseId;
		}
		
		
		public Semester getSemester() {
			return semester;
		}
		public void setSemester(Semester semester) {
			this.semester = semester;
		}
		public Course getCourse() {
			return course;
		}
		public void setCourse(Course course) {
			this.course = course;
		}
		public User getTeacher() {
			return teacher;
		}
		public void setTeacher(User teacher) {
			this.teacher = teacher;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		@Override
		public int getId() {
			return this.getSemesterCourseId();
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


