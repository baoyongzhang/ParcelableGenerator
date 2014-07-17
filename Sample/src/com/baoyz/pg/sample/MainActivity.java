package com.baoyz.pg.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baoyz.pg.PG;
import com.baoyz.pg.sample.model.Classroom;
import com.baoyz.pg.sample.model.Student;
import com.baoyz.pg.sample.model.Teacher;
import com.baoyz.pg.sample.model.User;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showUser(View v) {

		// create model
		User user = new User();
		user.setName("zhangsan");
		user.setAge(18);
		user.setBalance(100.85);
		user.setId(11111l);
		user.setVip(true);
		user.setAlias(Arrays.asList("alias1", "alias2", "alias3"));

		Intent intent = new Intent(this, ShowUserActivity.class);
		// model convert to parceable
		intent.putExtra("user", PG.convertParcelable(user));
		startActivity(intent);
	}

	public void showClassroom(View v) {
		// classroom object
		Classroom room = new Classroom("classroomName");
		Teacher teacher = new Teacher("teacherName", "course");
		// set teacher object
		room.setTeacher(teacher);
		// create students list
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("stu1", 18));
		students.add(new Student("stu2", 19));
		students.add(new Student("stu3", 20));
		// set students list
		room.setStudents(students);

		Intent intent = new Intent(this, ShowClassroomActivity.class);
		// classroom object convert to parceable object
		intent.putExtra("classroom", PG.convertParcelable(room));
		startActivity(intent);
	}

}
