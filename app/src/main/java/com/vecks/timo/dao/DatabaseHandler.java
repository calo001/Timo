package com.vecks.timo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vecks.timo.models.Categoria;
import com.vecks.timo.models.Materia;
import com.vecks.timo.models.Tarea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by carlos on 10/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    // Todas las variables static
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Nombre de Database
    private static final String DATABASE_NAME = "tododb";

    // Nombre de la tabla Materia
    private static final String TABLA_MATERIA = "materia";

    // Nombre de las columnas de la Tabla Materia
    private static final String ID_MATERIA = "id";
    private static final String NOMBRE_MATERIA = "nombre";

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
    private static final String ID_MATERIA_FK = "id_materia";
    private static final String STATUS = "status";
    private static final String IMPORTANCIA = "importancia";
    private static final String DETALLE = "detalle";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creando Tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLA_MATERIA = "CREATE TABLE " + TABLA_MATERIA + " ("
                + ID_MATERIA + " INTEGER PRIMARY KEY,"
                + NOMBRE_MATERIA + " VARCHAR(50)"
                + ")";

        String CREATE_TABLA_CATEGORIA = "CREATE TABLE " + TABLA_CATEGORIA + " ("
                + ID_CATEGORIA + " INTEGER PRIMARY KEY,"
                + NOMBRE_CATEGORIA + " VARCHAR(50)"
                + ")";

        String CREATE_TABLA_TAREA = "CREATE TABLE " + TABLA_TAREA + " ("
                + ID_TAREA + " INTEGER PRIMARY KEY,"
                + NOMBRE_TAREA + " VARCHAR(50),"
                + FECHA_ENTREGA + " DATE,"
                + ID_CATEGORIA_FK + " INTEGER,"
                + ID_MATERIA_FK + " INTEGER,"
                + STATUS + " INTEGER,"
                + IMPORTANCIA + " INTEGER,"
                + DETALLE + " VARCHAR(140)"
                + ")";

        db.execSQL(CREATE_TABLA_MATERIA);
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

    // Obtener uuna lista de todas las Materias
    public List<Materia> getAllMateria(){
        List<Materia> listaMaterias = new ArrayList<Materia>();

        // Query Select * from materias
        String selectQuery = "SELECT  * FROM " + TABLA_MATERIA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Iterando el resultado del query y agregando a la lista
        if (cursor.moveToFirst()) {
            do {
                Materia materia = new Materia();
                materia.setId( Integer.parseInt(cursor.getString(0)) );
                materia.setNombre(cursor.getString(1));

                listaMaterias.add(materia);
            } while (cursor.moveToNext());
        }

        // return contact list
        return listaMaterias;
    }

    // Agrega una nueva maateria
    public void addMateria(Materia nuevaMateria) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Obtener los valores para el Insert del objeto que representa una nueva materia
        ContentValues values = new ContentValues();
        values.put(ID_MATERIA, nuevaMateria.getId());
        values.put(NOMBRE_MATERIA, nuevaMateria.getNombre());

        // Inserta fila
        db.insert(TABLA_MATERIA, null, values);
        db.close();
    }

    // Obtener Tareas por Asignatura
    public List<Tarea> getTareasPoMateria(){
        List<Tarea> listaTarea = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLA_TAREA;
        return null;
    }

}
