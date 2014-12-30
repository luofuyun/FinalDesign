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
@Table(name = "t_classdoc")
public class ClassDoc extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int classDocId;

	@ManyToOne
	@JoinColumn
	private Class_bean docClass;

	@ManyToOne
	@JoinColumn
	private Document document;

	@Column(name = "valid")
	private int valid;

	@Column(name = "create_date")
	private Date createDate;

	public int getClassDocId() {
		return classDocId;
	}

	public void setClassDocId(int classDocId) {
		this.classDocId = classDocId;
	}

	public Class_bean getDocClass() {
		return docClass;
	}

	public void setDocClass(Class_bean docClass) {
		this.docClass = docClass;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public int getId() {
		return this.getClassDocId();
	}

}
