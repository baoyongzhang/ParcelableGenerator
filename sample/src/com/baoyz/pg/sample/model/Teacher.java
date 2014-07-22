package com.baoyz.pg.sample.model;

import com.baoyz.pg.Parcelable;

@Parcelable
public class Teacher {

	private String name;
	private String course;

	// required
	public Teacher() {
	}

	public Teacher(String name, String course) {
		super();
		this.name = name;
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", course=" + course + "]";
	}

}
