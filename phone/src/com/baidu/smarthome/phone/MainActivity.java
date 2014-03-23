package com.baidu.smarthome.phone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baidu.smarthome.phone.dao.DaoSession;
import com.baidu.smarthome.phone.dao.Member;
import com.baidu.smarthome.phone.dao.MemberDao;

public class MainActivity extends Activity {
	ListView mListView;
	MemberAdapter mAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupDB();

		mListView = (ListView) findViewById(R.id.list);

		mAdapter = new MemberAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(MainActivity.this,
						com.baidu.smarthome.phone.MsgHistoryActivity.class);
				long peerid = ((Member)mAdapter.getItem(arg2)).getId();
				intent.putExtra("peerid", peerid);
				startActivity(intent);
			}
		});
	}

	private void setupDB() {
		SharedPreferences settings = getSharedPreferences("settings", 0);
		boolean DBinit = settings.getBoolean("dbinit", false);
		if (false == DBinit) {
			DaoSession daoSession = SmartHomeApplication.getDaoMaster()
					.newSession();
			MemberDao memberDao = daoSession.getMemberDao();
			Member dad = new Member();
			dad.setName(getString(R.string.dad));
			dad.setAvatarid(R.drawable.grandpa);
			dad.setPosition(getString(R.string.home));
			dad.setAthome(true);
			memberDao.insert(dad);
			Member mum = new Member();
			mum.setName(getString(R.string.mum));
			mum.setAvatarid(R.drawable.grandma);
			mum.setPosition(getString(R.string.market));
			mum.setAthome(false);
			memberDao.insert(mum);
			Member daughter = new Member();
			daughter.setName(getString(R.string.daughter));
			daughter.setAvatarid(R.drawable.daughter);
			daughter.setPosition(getString(R.string.park));
			daughter.setAthome(false);
			memberDao.insert(daughter);
			Member son = new Member();
			son.setName(getString(R.string.son));
			son.setAvatarid(R.drawable.son);
			son.setPosition(getString(R.string.school));
			son.setAthome(false);
			memberDao.insert(son);
			Member wife = new Member();
			wife.setName(getString(R.string.wife));
			wife.setAvatarid(R.drawable.mama);
			wife.setPosition(getString(R.string.company));
			wife.setAthome(false);
			memberDao.insert(wife);
			Member husband = new Member();
			husband.setName(getString(R.string.husband));
			husband.setAvatarid(R.drawable.dad);
			husband.setPosition(getString(R.string.company));
			husband.setAthome(false);
			memberDao.insert(husband);
			
			settings.edit().putBoolean("dbinit", true).commit();
		}
	}
}