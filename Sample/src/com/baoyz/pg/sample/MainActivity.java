package com.baoyz.pg.sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.baoyz.pg.library.PG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv = (TextView) findViewById(R.id.textView);

		findViewById(R.id.bt).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ShowUserActivity.class);
		User user = new User();
		user.setName("zhangsan");
		user.setAge(18);
		intent.putExtra("user", PG.createParcelable(user));
		startActivity(intent);
	}

}
