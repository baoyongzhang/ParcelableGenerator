package com.baoyz.pg.sample.model;

import java.util.List;

import com.baoyz.pg.Parcelable;

@Parcelable
public class Classroom {

	private Teacher teacher;
	private List<Student> students;

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
		return "Classroom [teacher=" + teacher + ", students=" + students + "]";
	}

}
