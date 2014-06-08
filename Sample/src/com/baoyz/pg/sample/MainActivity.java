package com.baoyz.pg.sample;

import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baoyz.pg.PG;

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
		
		// 设置对象的属性
		User user = new User();
		user.setName("zhangsan");
		user.setAge(18);
		user.setBalance(100.85);
		user.setId(11111l);
		user.setList(Arrays.asList("哈哈哈", "呵呵呵", "哇哇哇"));
		user.setVip(true);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "value1");
		map.put(2, "value2");
		user.setMap(map);
		user.setStrArray(new String[] { "str1", "str2" });
		user.setIntArray(new int[] { 1, 2, 3, 4, 5 });
		
		Intent intent = new Intent(this, ShowUserActivity.class);
		// 调用PG将对象转换成Parcelable，传入Intent
		intent.putExtra("user", PG.createParcelable(user));
		startActivity(intent);
	}

}
