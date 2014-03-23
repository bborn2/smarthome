package com.baidu.smarthome.phone.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.baidu.smarthome.phone.dao.Member;
import com.baidu.smarthome.phone.dao.Message;

import com.baidu.smarthome.phone.dao.MemberDao;
import com.baidu.smarthome.phone.dao.MessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig memberDaoConfig;
    private final DaoConfig messageDaoConfig;

    private final MemberDao memberDao;
    private final MessageDao messageDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        memberDaoConfig = daoConfigMap.get(MemberDao.class).clone();
        memberDaoConfig.initIdentityScope(type);

        messageDaoConfig = daoConfigMap.get(MessageDao.class).clone();
        messageDaoConfig.initIdentityScope(type);

        memberDao = new MemberDao(memberDaoConfig, this);
        messageDao = new MessageDao(messageDaoConfig, this);

        registerDao(Member.class, memberDao);
        registerDao(Message.class, messageDao);
    }
    
    public void clear() {
        memberDaoConfig.getIdentityScope().clear();
        messageDaoConfig.getIdentityScope().clear();
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

}
