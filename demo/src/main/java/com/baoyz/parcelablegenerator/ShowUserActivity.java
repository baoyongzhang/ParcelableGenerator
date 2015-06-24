package com.baoyz.parcelablegenerator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.baoyz.parcelablegenerator.model.User;

public class ShowUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		TextView tv = (TextView) findViewById(R.id.textView);
		
		// get model object
		User user = getIntent().getParcelableExtra("user");
		
		// use model
		tv.setText(user.toString());
	}

}
