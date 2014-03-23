package com.baidu.smarthome.phone;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MsgHistoryActivity extends Activity {
	/** Called when the activity is first created. */
	private ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_history);

		list = (ListView) findViewById(R.id.list);
		MessageAdapter adapter = new MessageAdapter(MsgHistoryActivity.this);
		list.setAdapter(adapter);
	}
}