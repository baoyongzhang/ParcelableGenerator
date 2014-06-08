package com.baoyz.pg.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.textView);
		
		// 直接获取原对象类型
		User user = getIntent().getParcelableExtra("user");
		
		// 使用对象的属性，toString()所有属性值
		tv.setText(user.toString());
	}

}
