package com.baoyz.pg.sample.model;

import java.util.List;

import com.baoyz.pg.Parcelable;

@Parcelable
public class Classroom {

	private String name;
	private Teacher teacher;
	private List<Student> students;
	
	public Classroom() {
	}

	public Classroom(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Classroom [name=" + name + ", teacher=" + teacher
				+ ", students=" + students + "]";
	}

}
