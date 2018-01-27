package com.zycus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "zcart_feedback")
public class Feedback {
	@Id
	@GenericGenerator(strategy = "increment", name = "inc")
	@GeneratedValue(generator = "inc")
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String msg;

	public Feedback() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Feedback(int id, String name, String msg) {
		super();
		this.id = id;
		this.name = name;
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", name=" + name + ", msg=" + msg + "]";
	}
	
	

}
