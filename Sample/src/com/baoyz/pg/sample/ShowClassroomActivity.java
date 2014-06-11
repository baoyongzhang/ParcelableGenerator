package com.baoyz.pg.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.baoyz.pg.sample.model.Classroom;

public class ShowClassroomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		TextView tv = (TextView) findViewById(R.id.textView);
		
		// 直接获取原对象类型
		Classroom room = getIntent().getParcelableExtra("classroom");
		
		// 使用对象的属性，toString()所有属性值
		tv.setText(room.toString());
	}

}
