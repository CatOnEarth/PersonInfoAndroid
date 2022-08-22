package com.snail.personinfo.db;

import android.provider.BaseColumns;

/**
 *
 */
public class ContactDatabase {
    public static final String DB_NAME    = "persons.db";
    public static final int    DB_VERSION = 1;

    public static final String[] CREATE_DATABASE_QUERIES = {
        PersonsTable.CREATE_TABLE
    };

    public static abstract class PersonsTable implements BaseColumns {

        public static String TABLE_NAME = "persons";

        public static final String NAME_COLUMN     = "name";
        public static final String SURNAME_COLUMN  = "surname";
        public static final String EMAIL_COLUMN    = "email";
        public static final String AGE_COLUMN      = "age";
        public static final String GENDER_COLUMN   = "gender";

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER, "       +
                        "%s INTEGER);",
                TABLE_NAME,
                _ID,
                NAME_COLUMN,
                SURNAME_COLUMN,
                EMAIL_COLUMN,
                AGE_COLUMN,
                GENDER_COLUMN);
    }
}
