package com.krisitine.readbookpro.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kristine on 2016/1/24.
 */
public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context,"book",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE book("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT DEFAULT NONE,"+
                "page TEXT DEFAULT NONE,"+
                "cpage Text DEFAULT '0',"+
                "percent TEXT DEFAULT '0')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
