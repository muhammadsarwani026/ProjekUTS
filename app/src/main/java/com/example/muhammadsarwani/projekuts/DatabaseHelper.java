package com.example.muhammadsarwani.projekuts;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Muhammad Sarwani on 10/28/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myDB";
    public static final String DATABASE_VERSION = "1";
    public static final String TABLE_NAME = "transaksi";
    public static final String COL_1 = "id";
    public static final String COL_2 = "tanggal";
    public static final String COL_3 = "tipe";
    public static final String COL_4 = "nominal";
    public static final String COL_5 = "uraian";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, Integer.parseInt(DATABASE_VERSION));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                TABLE_NAME +
                "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COL_2 + " DATE ," +
                COL_3 + " VARCHAR(10), " +
                COL_4 + " LONG, " +
                COL_5 + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mycontacts");
        onCreate(db);
    }

    public boolean insert(Integer id ,String tanggal, String tipe, String nominal,String uraian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("id", id);
        contentValues.put("tanggal", tanggal);
        contentValues.put("tipe", tipe);
        contentValues.put("nominal", nominal);
        contentValues.put("uraian", uraian);
        db.insert("transaksi", null, contentValues);
        db.close();
        return true;
    }

    public boolean update(Integer id, String tanggal, String tipe, String nominal,String uraian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("id", id);
        contantValues.put("tanggal", tanggal);
        contantValues.put("tipe", tipe);
        contantValues.put("nominal", nominal);
        contantValues.put("uraian", uraian);
        db.update("transaksi", contantValues, "id = ?", new String[]{Integer.toString(id)});
        db.close();
        return true;
    }

    public long delete(Integer id) {
        String where = COL_1 + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,where,null);
    }

    public Cursor getData(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("Select * from transaksi where id = " + id + "", null);
    }

    public int numberOfRows(){
        SQLiteDatabase db=this.getWritableDatabase();
        int numRows=(int) DatabaseUtils.queryNumEntries(db,TABLE_NAME);
        return numRows;
    }

}
