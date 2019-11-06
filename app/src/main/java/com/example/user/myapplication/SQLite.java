package com.example.user.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;

public class SQLite {

    private  sql sql;
    private SQLiteDatabase db;
    public SQLite(Context context){

        sql=new sql(context);
    }

    public SQLite() {

    }

    public void abrir(){
        Log.i("SQLite","Se abre conexion a la base de datos "+sql.getDatabaseName());
        db=sql.getWritableDatabase();

    }
    public void cerrar(){
        Log.i("SQLite","Se cierra conexion a la base de datos "+sql.getDatabaseName());
        sql.close();
    }
    public boolean addRegistroProductos(String nombre, int cantidad, String cum, String ing,String res,String lugar){
        ContentValues cv=new ContentValues();

        cv.put("NOMBRE",nombre);
        cv.put("CANTIDAD",cantidad);
        cv.put("FECHA_VEN",cum);
        cv.put("FECHA_ING",ing);
        cv.put("FECHA_OUT",res);
        cv.put("LUGAR",lugar);

        return (db.insert("PRODUCTOS",null,cv)!=-1)?true:false;
    }

    public Cursor getRegistrosProductos(){
        return db.rawQuery("SELECT * FROM PRODUCTOS",null);
    }

    public ArrayList<String> getPROD(Cursor cur){
        ArrayList<String> listData=new ArrayList<String>();
        String item="";
        if(cur.moveToFirst()){
            do{
                item+="ID: "+cur.getInt(0)+"\r\n";
                item+="Nombre: "+cur.getString(1)+"\r\n";
                item+="Cantidad en existencia: "+cur.getString(2)+"\r\n";
                item+="fecha de vencimiento: "+cur.getString(3)+"\r\n";
                item+="fecha de ingreso: "+cur.getString(4)+"\r\n";
                item+="ultima fecha de retiro: "+cur.getString(5)+"\r\n";
                item+="lugar que recibió: "+cur.getString(6)+"\r\n";
                listData.add(item);
                item="";
            }while (cur.moveToNext());
        }
        return listData;
    }
    public ArrayList<String> getID(Cursor cur){
        ArrayList<String> listData=new ArrayList<String>();
        String item="";
        if(cur.moveToFirst()){
            do{
                item+="ID: "+cur.getInt(0)+"\r\n";
                item="";
            }while (cur.moveToNext());
        }
        return listData;
    }


    public String addUpdateProd(int id,String nombre, int org, String cum, String ing, String res, String lugar){
        ContentValues registro= new ContentValues();
        registro.put("NOMBRE",nombre);
        registro.put("CANTIDAD",org);
        registro.put("FECHA_VEN",cum);
        registro.put("FECHA_ING",ing);
        registro.put("FECHA_OUT",res);
        registro.put("LUGAR",lugar);
        int cant= db.update("PRODUCTOS", registro,"ID_PROD="+id,null);
        if(cant==1){
            return "Usuario Mod";
        }
        else
            return "Fallo algo";
    }
    public Cursor getCant(int id){
        return db.rawQuery("SELECT * FROM PRODUCTOS WHERE ID_PROD = "+id,null);
    }

    public int  Eliminar(Editable id){
        return db.delete("PRODUCTOS","ID_PROD="+id,null);
    }


}