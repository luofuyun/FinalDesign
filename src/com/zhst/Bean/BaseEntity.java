package com.zhst.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract int getId();

	@Column(name = "valid")
	private int valid;
	
	@Column(name = "create_date")
	private Date createDate;

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
