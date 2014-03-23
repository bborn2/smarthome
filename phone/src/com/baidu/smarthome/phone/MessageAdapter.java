package com.baidu.smarthome.phone;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.smarthome.phone.dao.DaoSession;
import com.baidu.smarthome.phone.dao.Member;
import com.baidu.smarthome.phone.dao.MemberDao;
import com.baidu.smarthome.phone.dao.MemberDao.Properties;
import com.baidu.smarthome.phone.dao.Message;
import com.baidu.smarthome.phone.dao.MessageDao;

import de.greenrobot.dao.query.QueryBuilder;

public class MessageAdapter extends BaseAdapter {

	private List<Message> mTalkList;
	private Context mContext;
	
	DaoSession mDaoSession;
	MessageDao mMessageDao;
	MemberDao mMemberDao;
	Member mPeerMember;
	
	private long mPeerId;

	public MessageAdapter(Context context, long peerId) {
		super();
		mPeerId = peerId;
		mDaoSession = SmartHomeApplication.getDaoMaster().newSession();
        mMessageDao = mDaoSession.getMessageDao();
        mMemberDao = mDaoSession.getMemberDao();
        QueryBuilder<Member> qb = mMemberDao.queryBuilder();
        qb.where(Properties.Id.eq(mPeerId));
        List<Member> result = qb.list();
        if (null != result && !result.isEmpty()) {
        	mPeerMember = result.get(0);
        	mTalkList = result.get(0).getMessagelist();
        } else {
        	mTalkList = new ArrayList<Message>();
        }
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTalkList.size();
	}

	public void intertMessage(Message msg) {
		mTalkList = mPeerMember.getMessagelist();
		msg.setPeerid(mPeerId);
		mDaoSession.insert(msg);
		mTalkList.add(msg);
		
		this.notifyDataSetChanged();
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
		Message msg = mTalkList.get(position);
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);

			convertView = inflater.inflate(R.layout.talk, null);

		}
		TextView text = (TextView) convertView.findViewById(R.id.talk);

		text.setText(msg.getCotent());
		LinearLayout parent1 = (LinearLayout) convertView
				.findViewById(R.id.parent1);
		LinearLayout parent2 = (LinearLayout) convertView
				.findViewById(R.id.parent2);
		if (msg.getPeermsg()) {

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
}
