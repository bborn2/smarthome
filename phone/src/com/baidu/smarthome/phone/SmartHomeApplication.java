package com.baidu.smarthome.phone;

import android.app.Application;
import android.content.Context;

import com.baidu.smarthome.phone.dao.DaoMaster;
import com.baidu.smarthome.phone.dao.DaoMaster.OpenHelper;
import com.baidu.smarthome.phone.dao.DaoSession;

public class SmartHomeApplication extends Application {
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	
	private static SmartHomeApplication mInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}
	
	public static SmartHomeApplication getInstance() {
		return mInstance;
	}
	
	private static final String DATABASE_NAME = "smarthome_db";
	/**
	 * 取得DaoMaster
	 *
	 * @param context
	 * @return
	 */
	public static DaoMaster getDaoMaster()
	{
	    if (daoMaster == null)
	    {
	    	Context context = mInstance;
	        OpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
	        daoMaster = new DaoMaster(helper.getWritableDatabase());
	    }
	    return daoMaster;
	}
	/**
	 * 取得DaoSession
	 *
	 * @param context
	 * @return
	 */
	public static DaoSession getDaoSession()
	{
	    if (daoSession == null)
	    {
	        if (daoMaster == null)
	        {
	            daoMaster = getDaoMaster();
	        }
	        daoSession = daoMaster.newSession();
	    }
	    return daoSession;
	}
}
