package com.baidu.smarthome.phone;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.smarthome.phone.dao.DaoSession;
import com.baidu.smarthome.phone.dao.Member;
import com.baidu.smarthome.phone.dao.MemberDao;

public class MemberAdapter extends BaseAdapter {
	private List<Member> mPeopleList;
	private Context mContext;

	public MemberAdapter(Context context) {
		super();
		DaoSession daoSession = SmartHomeApplication.getDaoMaster().newSession();
        MemberDao memberDao = daoSession.getMemberDao();
        mPeopleList = memberDao.loadAll();
		mContext = context;
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
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater
					.from(mContext);
			convertView = inflater.inflate(R.layout.list_item, null);
		}
		
		String name = mPeopleList.get(position).getName();
		TextView nameTextView = (TextView) convertView.findViewById(R.id.title);
		nameTextView.setText(name);
		
		ImageView photo = (ImageView) convertView.findViewById(R.id.avatar);
		photo.setImageResource(R.drawable.mama);
		
		ImageView mask = (ImageView) convertView.findViewById(R.id.mask);
		if (mPeopleList.get(position).getAthome()) {
			mask.setVisibility(View.GONE);
		} else {
			mask.setVisibility(View.VISIBLE);
		}
		
		String memberPosition = mPeopleList.get(position).getPosition();
		TextView positionTextView = (TextView) convertView.findViewById(R.id.position);
		positionTextView.setText(memberPosition);
		
		return convertView;
	}

}
