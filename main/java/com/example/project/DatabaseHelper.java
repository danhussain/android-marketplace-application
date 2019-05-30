package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Project.db";

    private static final String TABLE_NAME_USER = "user";
    private static final String TABLE_NAME_POST = "post";

    private static final String COLUMN_NAME_USER_ID = "userID";
    private static final String COLUMN_NAME_POST_ID = "postID";

    private static final String COLUMN_NAME_FIRST_NAME = "firstName";
    private static final String COLUMN_NAME_LAST_NAME = "lastName";
    private static final String COLUMN_NAME_EMAIL = "email";
    private static final String COLUMN_NAME_PASSWORD = "password";

    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_DESCRIPTION = "description";
    private static final String COLUMN_NAME_PRICE = "price";

    private static final String SQL_CREATE_ENTRIES_USER = "CREATE TABLE " + TABLE_NAME_USER + " (" +
            COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_FIRST_NAME + " TEXT," +
            COLUMN_NAME_LAST_NAME + " TEXT," +
            COLUMN_NAME_EMAIL + " TEXT," +
            COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_POST = "CREATE TABLE " + TABLE_NAME_POST + " (" +
            COLUMN_NAME_POST_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_TITLE + " TEXT," +
            COLUMN_NAME_DESCRIPTION + " TEXT," +
            COLUMN_NAME_PRICE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_USER = "DROP TABLE IF EXISTS " + TABLE_NAME_USER;
    private static final String SQL_DELETE_ENTRIES_POST = "DROP TABLE IF EXISTS " + TABLE_NAME_POST;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_POST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_POST);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    User insertUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put(COLUMN_NAME_FIRST_NAME, u.firstName);
        values.put(COLUMN_NAME_LAST_NAME, u.lastName);
        values.put(COLUMN_NAME_EMAIL, u.email);
        values.put(COLUMN_NAME_PASSWORD, u.password);

        u.userID = db.insert(TABLE_NAME_USER, null, values);

        return u;
    }

    User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_NAME_USER_ID + ", " + COLUMN_NAME_PASSWORD + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_NAME_EMAIL + " ='"+email+"'";
        User u = new User(0, email, "", "", "");
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {
                u.userID=c.getLong(0);
                u.password=c.getString(1);
            } while (c.moveToNext());
        }
        c.close();
        return u;
    }

    Post insertPost(Post p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put(COLUMN_NAME_TITLE, p.title);
        values.put(COLUMN_NAME_DESCRIPTION, p.description);
        values.put(COLUMN_NAME_PRICE, p.price);

        p.postID = db.insert(TABLE_NAME_POST, null, values);

        return p;
    }

    Post getPost() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_POST;
        Post p = new Post(0, "", "", "");
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {
                p.postID=c.getLong(0);
                p.title=c.getString(1);
                p.description=c.getString(2);
                p.price=c.getString(3);
            } while (c.moveToNext());
        }
        c.close();
        return p;
    }
}
