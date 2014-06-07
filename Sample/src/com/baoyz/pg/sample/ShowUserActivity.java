package com.baoyz.pg.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		User user = getIntent().getParcelableExtra("user");
		
		TextView tv = (TextView) findViewById(R.id.textView);
		tv.setText(user.getName() + ", " + user.getAge());
	}

}
