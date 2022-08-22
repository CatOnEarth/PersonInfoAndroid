package com.snail.personinfo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.snail.personinfo.logger.Logger;
import com.snail.personinfo.Person;

/**
 *
 */
public class DBHelper  extends SQLiteOpenHelper{
    /**
     * < TAG for class name debug
     */
    private final String TAG = "DBHelper";
    /**
     *
     */
    private       Logger logger;

    /**
     *
     */
    public static final int    DATABASE_VERSION = 1;
    /**
     *
     */
    public static final String DATABASE_NAME = "personsDB";
    /**
     *
     */
    public static final String TABLE_PERSONS = "persons";
    /**
     *
     */
    public static final String KEY_ID      = "_id";
    /**
     *
     */
    public static final String KEY_NAME    = "name";
    /**
     *
     */
    public static final String KEY_SURNAME = "surname";
    /**
     *
     */
    public static final String KEY_EMAIL   = "email";
    /**
     *
     */
    public static final String KEY_AGE     = "age";
    /**
     *
     */
    public static final String KEY_GENDER  = "gender";

    /**
     *
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        logger = new Logger();

        db.execSQL("create table " + TABLE_PERSONS + "(" +
                KEY_ID       + " integer primary key," +
                KEY_NAME     + " text,"                +
                KEY_SURNAME  + " text,"                +
                KEY_EMAIL    + " text,"                +
                KEY_AGE      + " integer,"             +
                KEY_GENDER   + " integer"              +
                ")");

    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PERSONS);

        onCreate(db);

    }

    /**
     *
     * @param person
     */
    public void insertPerson(Person person) {
        logger = new Logger();
        logger.LogInfo(TAG, "call insertPerson");

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME,    person.getName());
        contentValues.put(DBHelper.KEY_SURNAME, person.getSurname());
        contentValues.put(DBHelper.KEY_EMAIL,   person.getEmail());
        contentValues.put(DBHelper.KEY_AGE,     person.getAge());
        contentValues.put(DBHelper.KEY_GENDER,  person.getGender());

        SQLiteDatabase database = getWritableDatabase();
        long res_insert         = database.insert(TABLE_PERSONS, null, contentValues);
        if (res_insert == -1) {
            logger.LogErr(TAG, "insert person error");
        } else {
            logger.LogInfo(TAG, "insert person success");
        }
    }
}