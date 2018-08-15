package com.baiwei.tianlong.jindong.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.baiwei.tianlong.jindong.mvp.search.model.beans.KeyWordsBeans;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "keywords".
*/
public class KeyWordsBeansDao extends AbstractDao<KeyWordsBeans, Long> {

    public static final String TABLENAME = "keywords";

    /**
     * Properties of entity KeyWordsBeans.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Keywords = new Property(1, String.class, "keywords", false, "KEYWORDS");
    };


    public KeyWordsBeansDao(DaoConfig config) {
        super(config);
    }
    
    public KeyWordsBeansDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"keywords\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"KEYWORDS\" TEXT);"); // 1: keywords
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"keywords\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KeyWordsBeans entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String keywords = entity.getKeywords();
        if (keywords != null) {
            stmt.bindString(2, keywords);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KeyWordsBeans entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String keywords = entity.getKeywords();
        if (keywords != null) {
            stmt.bindString(2, keywords);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public KeyWordsBeans readEntity(Cursor cursor, int offset) {
        KeyWordsBeans entity = new KeyWordsBeans( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // keywords
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KeyWordsBeans entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKeywords(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(KeyWordsBeans entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(KeyWordsBeans entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
