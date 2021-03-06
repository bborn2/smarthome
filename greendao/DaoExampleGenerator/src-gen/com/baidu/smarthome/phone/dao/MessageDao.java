package com.baidu.smarthome.phone.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.baidu.smarthome.phone.dao.Message;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MESSAGE.
*/
public class MessageDao extends AbstractDao<Message, Long> {

    public static final String TABLENAME = "MESSAGE";

    /**
     * Properties of entity Message.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Cotent = new Property(1, String.class, "cotent", false, "COTENT");
        public final static Property Peerid = new Property(2, long.class, "peerid", false, "PEERID");
        public final static Property Peermsg = new Property(3, boolean.class, "peermsg", false, "PEERMSG");
        public final static Property Timestamp = new Property(4, java.util.Date.class, "timestamp", false, "TIMESTAMP");
    };

    private Query<Message> member_MessagelistQuery;

    public MessageDao(DaoConfig config) {
        super(config);
    }
    
    public MessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MESSAGE' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'COTENT' TEXT NOT NULL ," + // 1: cotent
                "'PEERID' INTEGER NOT NULL ," + // 2: peerid
                "'PEERMSG' INTEGER NOT NULL ," + // 3: peermsg
                "'TIMESTAMP' INTEGER NOT NULL );"); // 4: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MESSAGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Message entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getCotent());
        stmt.bindLong(3, entity.getPeerid());
        stmt.bindLong(4, entity.getPeermsg() ? 1l: 0l);
        stmt.bindLong(5, entity.getTimestamp().getTime());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Message readEntity(Cursor cursor, int offset) {
        Message entity = new Message( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // cotent
            cursor.getLong(offset + 2), // peerid
            cursor.getShort(offset + 3) != 0, // peermsg
            new java.util.Date(cursor.getLong(offset + 4)) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Message entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCotent(cursor.getString(offset + 1));
        entity.setPeerid(cursor.getLong(offset + 2));
        entity.setPeermsg(cursor.getShort(offset + 3) != 0);
        entity.setTimestamp(new java.util.Date(cursor.getLong(offset + 4)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Message entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Message entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "messagelist" to-many relationship of Member. */
    public List<Message> _queryMember_Messagelist(long peerid) {
        synchronized (this) {
            if (member_MessagelistQuery == null) {
                QueryBuilder<Message> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Peerid.eq(null));
                queryBuilder.orderRaw("TIMESTAMP ASC");
                member_MessagelistQuery = queryBuilder.build();
            }
        }
        Query<Message> query = member_MessagelistQuery.forCurrentThread();
        query.setParameter(0, peerid);
        return query.list();
    }

}
