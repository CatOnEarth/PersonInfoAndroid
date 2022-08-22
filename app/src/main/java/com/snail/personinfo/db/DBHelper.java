package com.snail.personinfo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.snail.personinfo.logger.Logger;
import com.snail.personinfo.Person;

/** Class for work with Database SQLITE
 *
 */
public class DBHelper  extends SQLiteOpenHelper{
    /** TAG */
    private final String TAG = "DBHelper";
    /** logger */
    private       Logger logger;
    /** Database version */
    public static final int    DATABASE_VERSION = 1;
    /** Database name */
    public static final String DATABASE_NAME = "personsDB";
    /** Table name for save info about persons */
    public static final String TABLE_PERSONS = "persons";
    /** Column name _id */
    public static final String KEY_ID      = "_id";
    /** Column name name */
    public static final String KEY_NAME    = "name";
    /** Column name surname */
    public static final String KEY_SURNAME = "surname";
    /** Column name email */
    public static final String KEY_EMAIL   = "email";
    /** Column name age */
    public static final String KEY_AGE     = "age";
    /** Column name gender */
    public static final String KEY_GENDER  = "gender";

    /** Constructor for DBHelper
     *
     * @param context Context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Call if database not exist
     *
     * @param db SQLITE
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

    /**Call if need to update version of database
     *
     * @param db SQLITE
     * @param oldVersion number of old version db
     * @param newVersion number of new version db
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PERSONS);

        onCreate(db);

    }

    /**Insert new person in person table
     *
     * @param person Object person, who need to add
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