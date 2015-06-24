package com.baoyz.parcelablegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.baoyz.parcelablegenerator.model.Classroom;
import com.baoyz.parcelablegenerator.model.Student;
import com.baoyz.parcelablegenerator.model.Teacher;
import com.baoyz.parcelablegenerator.model.User;
import com.baoyz.pg.PG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
