package com.baidu.smarthome.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdapter extends BaseAdapter {

	private List<Talk> mTalkList;
	private Context mContext;

	public MsgAdapter(Context context) {
		super();
		mContext = context;
		mTalkList = new ArrayList<Talk>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTalkList.size();
	}

	public void setList(List<Talk> talkList) {
		// TODO Auto-generated method stub
		this.mTalkList = talkList;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mTalkList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Talk talk = mTalkList.get(position);
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);

			convertView = inflater.inflate(R.layout.talk, null);

		}
		TextView text = (TextView) convertView.findViewById(R.id.talk);

		text.setText(talk.content);
		LinearLayout parent1 = (LinearLayout) convertView
				.findViewById(R.id.parent1);
		LinearLayout parent2 = (LinearLayout) convertView
				.findViewById(R.id.parent2);
		if (talk.isLeft) {

			text.setBackgroundResource(R.drawable.bg_recieved_msg);
			parent1.setGravity(android.view.Gravity.LEFT);
			parent2.setGravity(android.view.Gravity.LEFT);

		} else {
			text.setBackgroundResource(R.drawable.bg_sent_msg);
			parent1.setGravity(android.view.Gravity.RIGHT);
			parent2.setGravity(android.view.Gravity.RIGHT);
		}
		return convertView;
	}

	public void updateData(String fr, String content) {
			Talk talk = new Talk(fr, content);
//			if (i % 2 == 0) {
		        talk.isLeft = true;
//			} else {
//				talk.text = str;
//			}
		    talk.content = fr + "说" +content;
			mTalkList.add(talk);
			this.notifyDataSetChanged();
	}
}
