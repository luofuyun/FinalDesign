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
@Table(name="t_user_class")

	public class User_Class extends BaseEntity{

	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
	    private int userClassId;
		
		@ManyToOne
		@JoinColumn
		private User user;
		
		@ManyToOne
		@JoinColumn
	    private Class_bean clas;
		
	    @Column(name="type")
	    private int type;
	    
	    @Column(name="valid")
	    private int valid;
	    
	    @Column(name="create_date")
	    private Date createDate;
	    
		public int getUserClassId() {
			return userClassId;
		}

		public void setUserClassId(int userClassId) {
			this.userClassId = userClassId;
		}

		

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
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
		
		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		public Class_bean getClas() {
			return clas;
		}

		public void setClas(Class_bean clas) {
			this.clas = clas;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public User getUser() {
			return user;
		}

		
	}


