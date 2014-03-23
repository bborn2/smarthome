package com.baidu.smarthome.phone;


import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.baidu.smarthome.phone.dao.Message;

public class MsgHistoryActivity extends Activity {
	/** Called when the activity is first created. */
	private ListView mListView;
	MessageAdapter mAdapter;
	
	private long mPeerId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_history);

		mPeerId = getIntent().getLongExtra("peerid", -1);
		mListView = (ListView) findViewById(R.id.list);
		mAdapter = new MessageAdapter(MsgHistoryActivity.this, mPeerId);
		mListView.setAdapter(mAdapter);
		
		Button sendBtn = (Button) findViewById(R.id.send);
		sendBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText editText = (EditText) findViewById(R.id.msgedit);
				String msg = editText.getText().toString();
				if (!TextUtils.isEmpty(msg)) {
					Message newMsg = new Message();
					newMsg.setCotent(msg);
					newMsg.setPeermsg(false);
					newMsg.setPeerid(mPeerId);
					newMsg.setTimestamp(new Date());
					mAdapter.intertMessage(newMsg);
				}
				
				editText.setText("");
				InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
			}
		});
	}
}