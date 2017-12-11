package com.vecks.timo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by carlos on 10/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    // Todas las variables static
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Nombre de Database
    private static final String DATABASE_NAME = "tododb";

    // Nombre de la tabla Categoria
    private static final String TABLA_CATEGORIA = "categoria";

    // Nombre de las columnas de la Tabla Categoria
    private static final String ID_CATEGORIA = "id";
    private static final String NOMBRE_CATEGORIA = "nombre";

    // Nombre de la tabla Tarea
    private static final String TABLA_TAREA = "tarea";
    // Nombre de las columnas de la Tabla Tarea
    private static final String ID_TAREA = "id";
    private static final String NOMBRE_TAREA = "nombre";
    private static final String FECHA_ENTREGA = "fecha_entrega";
    private static final String ID_CATEGORIA_FK = "id_categoria";
    private static final String STATUS = "status";
    private static final String IMPORTANCIA = "importancia";
    private static final String DETALLE = "detalle";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creando Tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLA_CATEGORIA = "CREATE TABLE " + TABLA_CATEGORIA + " ("
                + ID_CATEGORIA + " INTEGER PRIMARY KEY,"
                + NOMBRE_CATEGORIA + " VARCHAR(50)"
                + ")";

        String CREATE_TABLA_TAREA = "CREATE TABLE " + TABLA_TAREA + " ("
                + ID_TAREA + " INTEGER PRIMARY KEY,"
                + NOMBRE_TAREA + " VARCHAR(50),"
                + FECHA_ENTREGA + " DATE,"
                + ID_CATEGORIA_FK + " INTEGER,"
                + STATUS + " INTEGER,"
                + IMPORTANCIA + " INTEGER,"
                + DETALLE + " VARCHAR(140)"
                + ")";

        db.execSQL(CREATE_TABLA_CATEGORIA);
        db.execSQL(CREATE_TABLA_TAREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Borra las Tablas de la version Anterior
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " +  TABLA_TAREA);

        // Crea de nuevo las Tablas
        onCreate(db);
    }
}
