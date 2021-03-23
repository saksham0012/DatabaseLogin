package com.example.loginregistrationdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USER_RECORD";
    private static final String TABLE_NAME = "USER_DATA";
    private static final String TABLE_API = "API_DATA";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PASSWORD";
    private static final String COL_A = "albumId";
    private static final String COL_B = "id";
    private static final String COL_C = "title";
    private static final String COL_D = "url";
    private static final String COL_E = "thumbnailUrl";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT , EMAIL TEXT , PASSWORD TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_API+"(albumId TEXT,id TEXT,title TEXT,url TEXT,thumbnailUrl TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_API);
        onCreate(db);
    }

    public boolean ImageApiRecord(String albumId , String id , String title ,String url , String thumbnailUrl){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_A , albumId);
        values.put(COL_B , id);
        values.put(COL_C , title);
        values.put(COL_D , url);
        values.put(COL_E , thumbnailUrl);

        long result = db.insert(TABLE_API , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean registerUser(String username , String email , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , username);
        values.put(COL_3 , email);
        values.put(COL_4 , password);

        long result = db.insert(TABLE_NAME , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String username , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { COL_1 };
        String selection = COL_2 + "=?" + " and " + COL_4 + "=?";
        String [] selectionargs = { username , password};
        Cursor cursor = db.query(TABLE_NAME , columns , selection ,selectionargs , null , null , null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count > 0)
            return true;
        else
            return false;

    }
}
