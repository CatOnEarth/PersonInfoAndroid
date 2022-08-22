package com.snail.personinfo.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.snail.personinfo.logger.Logger;
import com.snail.personinfo.Person;

import java.util.ArrayList;

/** Class for work with Database SQLITE
 *
 */
public class DBHelper  extends SQLiteOpenHelper {
    /** TAG */
    private final String TAG = "DBHelper";
    /** logger */
    private       Logger logger;
    /** Database version */
    private static final int    DATABASE_VERSION = 1;
    /** Database name */
    private static final String DATABASE_NAME = "personsDB";
    /** Table name for save info about persons */
    private static final String TABLE_PERSONS = "persons";
    /** Column name _id */
    private static final String KEY_ID      = "_id";
    /** Column name name */
    private static final String KEY_NAME    = "name";
    /** Column name surname */
    private static final String KEY_SURNAME = "surname";
    /** Column name email */
    private static final String KEY_EMAIL   = "email";
    /** Column name age */
    private static final String KEY_AGE     = "age";
    /** Column name gender */
    private static final String KEY_GENDER  = "gender";

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

    /** Clear DB
     *
     */
    public void deleteDB() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
        onCreate(database);

        //database.close();
    }

    /** Insert new person in person table
     *
     * @param person Object person, who need to add
     *
     * @return -1: error
     * @return -2: person exist in table
     * @return  another: id row in table
     */
    public long insertPerson(Person person) {
        logger = new Logger();
        logger.LogInfo(TAG, "call insertPerson");

        if (IsPersonExist(person)) {
            return -2;
        }

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

        database.close();

        return res_insert;
    }

    /** Select person from person table
     *
     * @return Selected person from person table
     */
    public ArrayList<Person> selectAllPersons() {
        logger = new Logger();
        logger.LogInfo(TAG, "call selectAllPersons");

        SQLiteDatabase database = getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE_PERSONS, null,
                null, null, null, null, null);

        ArrayList<Person> persons = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int nameColIndex    = cursor.getColumnIndex(KEY_NAME);
            int surnameColIndex = cursor.getColumnIndex(KEY_SURNAME);
            int emailColIndex   = cursor.getColumnIndex(KEY_EMAIL);
            int ageColIndex     = cursor.getColumnIndex(KEY_AGE);
            int genderColIndex  = cursor.getColumnIndex(KEY_GENDER);

            String textName;
            String textSurname;
            String textEmail;
            int    intAge;
            byte   byteGender;

            int count = 1;

            do {
                textName     = cursor.getString(nameColIndex);
                textSurname  = cursor.getString(surnameColIndex);
                textEmail    = cursor.getString(emailColIndex);
                intAge       = cursor.getInt(ageColIndex);
                byteGender   = (byte) cursor.getInt(genderColIndex);

                logger.LogInfo(TAG, "Person №" + count + ". Name: " + textName +
                        "; Surname: " + textSurname + "; Email: " + textEmail + "; Age: " + intAge +
                        "; Gender: " + String.valueOf(byteGender));

                persons.add(new Person(textName, textSurname, textEmail, intAge, byteGender));

                ++count;
            } while (cursor.moveToNext());
        } else {
            logger.LogInfo(TAG, "0 rows selected");
        }

        return persons;
    }

    /** Check if person exist in person table
     *
     * @param person Object person, who need to find
     * @return true: exist
     * @return false: not exist
     */
    public boolean IsPersonExist(Person person) {
        SQLiteDatabase database = getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE_PERSONS, null,
                KEY_NAME + " = ? AND " +
                KEY_SURNAME   + " = ? AND "          +
                KEY_EMAIL     + " = ? AND "          +
                KEY_AGE       + " = ? AND "          +
                KEY_GENDER    + " = ? "              ,
                new String[] {person.getName()                   ,
                              person.getSurname()                ,
                              person.getEmail()                  ,
                              String.valueOf(person.getAge())    ,
                              String.valueOf(person.getGender()) },
                null, null, null);

        boolean isExist = cursor.moveToFirst();
        cursor.close();
        database.close();

        return isExist;
    }
}