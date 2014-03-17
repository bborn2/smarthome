package com.baidu.smarthome.phone;

import java.util.ArrayList;
import java.util.List;

import com.baidu.smarthome.phone.MsgAdapter.Talk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PeopleAdapter extends BaseAdapter {
	private List<String> mPeopleList;
	private Context mContext;

	public PeopleAdapter(Context context) {
		super();
		init();
		mContext = context;
	}
	void init() {
		mPeopleList = new ArrayList<String>();
		mPeopleList.add("Ddd");
		mPeopleList.add("mum");
		mPeopleList.add("wife");
		mPeopleList.add("child");
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mPeopleList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mPeopleList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String name = mPeopleList.get(position);
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater
					.from(mContext);
			convertView = inflater.inflate(R.layout.list_item, null);
		}
		TextView text = (TextView) convertView.findViewById(R.id.title);
		text.setText(name);
		ImageView photo = (ImageView) convertView.findViewById(R.id.img);
		photo.setImageResource(R.drawable.item_left2);
		
		return convertView;
	}

}
