package com.example.user.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class sql extends SQLiteOpenHelper {

    private static  final String DATABASE="AGENDA";
    private static final int VERSION=1;
    //campos


    private final String tprod="CREATE TABLE PRODUCTOS( ID_PROD INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "NOMBRE TEXT NOT NULL, CANTIDAD INTEGER NOT NULL, FECHA_VEN TEXT NOT NULL," +
            "FECHA_ING TEXT NOT NULL,FECHA_OUT TEXT NOT NULL,LUGAR TEXT NOT NULL )";

    public sql(Context context){
        super(context,DATABASE,null,VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tprod);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL("DROP TABLE IF EXIST PERSONAS");
            db.execSQL(tprod);
        }
    }
}