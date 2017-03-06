package com.zapata.edwin.holamundo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;


import java.util.ArrayList;
import java.util.List;


public class DataBaseE extends SQLiteOpenHelper{

    private static final int VERSION_BASEDATOS = 1;

    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "mibasedatos.db";

    // Sentencia SQL para la creación de una tabla
    private static final String TABLA_DATOS = "CREATE TABLE gestos" +
            "(_id INT PRIMARY KEY, dedo TEXT, PosX DOUBLE, PosY DOUBLE)";


    // CONSTRUCTOR de la clase
    public DataBaseE(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_DATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_DATOS);
        onCreate(db);
    }

    public void insertarDato(int id, String dedo, Double PosX, Double PosY) {
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put("_id", id);
            valores.put("dedo", dedo);
            valores.put("PosX", PosX);
            valores.put("PosY", PosY);
            db.insert("gestos", null, valores);
            db.close();
        }
    }

    public void modificarDato(int id, String dedo, Double PosX, Double PosY){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put("dedo", dedo);
        valores.put("PosX", PosX);
        valores.put("PosY", PosY);
        db.update("gestos", valores, "_id=" + id, null);
        db.close();
    }

    public void borrarDato(int id) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.delete("gestos", "_id= "+id, null);
//        db.close();
    }

    public void borrarTodo(){

    }

    public DatosE recuperarDato(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", "dedo", "PosX", "PosY"};
        Cursor c = db.query("gestos", valores_recuperar, "_id=" + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        DatosE datosRecu = new DatosE(c.getInt(0), c.getString(1),
                c.getDouble(2), c.getDouble(3));
        db.close();
        c.close();
        return datosRecu;
    }


    public List<DatosE> recuperarDATOS() {
        SQLiteDatabase db = getReadableDatabase();
        List<DatosE> lista_Datos = new ArrayList<DatosE>();
        String[] valores_recuperar = {"_id", "dedo", "PosX", "PosY"};
        Cursor c = db.query("gestos", valores_recuperar,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            DatosE contactos = new DatosE(c.getInt(0), c.getString(1),
                    c.getDouble(2), c.getDouble(3));
            lista_Datos.add(contactos);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_Datos;
    }

}



