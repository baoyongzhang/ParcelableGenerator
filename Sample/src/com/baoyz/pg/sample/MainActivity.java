package com.baoyz.pg.sample;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.baoyz.pg.PG;
import com.baoyz.pg.PGUtils;
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
		// 简单情况

		// 设置对象的属性
		User user = new User();
		user.setName("zhangsan");
		user.setAge(18);
		user.setBalance(100.85);
		user.setId(11111l);
		user.setVip(true);
		user.setAlias(Arrays.asList("alias1", "alias2", "alias3"));
		
		Intent intent = new Intent(this, ShowUserActivity.class);
		// 调用PG将对象转换成Parcelable，传入Intent
		intent.putExtra("user", PG.convertParcelable(user));
		startActivity(intent);
	}

	public void showClassroom(View v) {
		// 复杂情况
		// 当对象中存在其他对象或者List中包含其他对象，而这些对象不可序列化，直接传递将会是null
		// 调用PG.convert()将不可序列化对象转为可序列化对象，返回类型与原类型一致。当然该类型需要@Parcelable修饰
		// 设置对象的属性
		Classroom room = new Classroom();
		Teacher teacher = new Teacher("teacherName", "course");
		// 转变为可序列化类型
		room.setTeacher(PG.convert(teacher));
		List<Student> students = new ArrayList<Student>();
		// 转变为可序列化类型
		students.add(PG.convert(new Student("stu1", 18)));
		students.add(PG.convert(new Student("stu2", 19)));
		students.add(PG.convert(new Student("stu3", 20)));
		room.setStudents(students);

		Intent intent = new Intent(this, ShowClassroomActivity.class);
		// 调用PG将对象转换成Parcelable，传入Intent
		intent.putExtra("classroom", PG.convertParcelable(room));
		startActivity(intent);
	}

}
