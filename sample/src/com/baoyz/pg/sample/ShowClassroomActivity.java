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
		
		// get model object
		Classroom room = getIntent().getParcelableExtra("classroom");
		
		// use model
		tv.setText(room.toString());
	}

}
