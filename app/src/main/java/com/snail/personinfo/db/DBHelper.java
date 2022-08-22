package com.snail.personinfo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     *
     * @param context
     */
    public DBHelper(Context context) {
        super(context, ContactDatabase.DB_NAME, null, ContactDatabase.DB_VERSION);
    }

    /**
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String query : ContactDatabase.CREATE_DATABASE_QUERIES) {
            sqLiteDatabase.execSQL(query);
        }
    }

    /**
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}