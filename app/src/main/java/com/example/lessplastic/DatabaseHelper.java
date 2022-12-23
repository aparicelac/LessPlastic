package com.example.lessplastic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lessPlastic.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USUARIO_TABLE = "USUARIOS";
    private static final String PLASTICOS_TABLE = "PLASTICOS";
    private static final String REGISTROS_TABLE = "REGISTROS";

    public static final String ID_USUARIO = "ID_usuario";
    public static final String NOMBRE = "nombre";
    public static final String NOMBRE_USUARIO = NOMBRE + "_usuario";
    public static final String CORREO = "correo";
    public static final String APELLIDO = "apellido";
    public static final String CONTRASEÑA = "contraseña";
    public static final String ID_PLASTICO = "ID_plastico";
    public static final String TIPO = "tipo";
    public static final String CANTIDAD = "cantidad";
    public static final String TAMAÑO = "tamaño";
    public static final String PESO = "peso";
    public static final String FECHA = "fecha";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String usuarioTable = "CREATE TABLE " + USUARIO_TABLE + "(" + ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMBRE_USUARIO + " TEXT, " + CORREO + " TEXT, " + NOMBRE + " TEXT, " + APELLIDO + " TEXT, " + CONTRASEÑA + " TEXT)";
        db.execSQL(usuarioTable);
        String plasticosTable = "CREATE TABLE " + PLASTICOS_TABLE + "(" + ID_PLASTICO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIPO + " TEXT, " + CANTIDAD + " INTEGER, " + TAMAÑO + " REAL, " + PESO + " REAL)";
        db.execSQL(plasticosTable);
        String registrosTable = "CREATE TABLE " + REGISTROS_TABLE + "(" + FECHA + " TEXT, " + ID_USUARIO + " INTEGER, " + ID_PLASTICO + " INTEGER, FOREIGN KEY(" + ID_USUARIO + ") REFERENCES " + USUARIO_TABLE + "(" + ID_USUARIO + "), FOREIGN KEY(" + ID_PLASTICO + ") REFERENCES " + PLASTICOS_TABLE + "(" + ID_PLASTICO + "))";
        db.execSQL(registrosTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (usuario != null) {
            cv.put(NOMBRE_USUARIO, usuario.getNombreUsuario());
            cv.put(CORREO, usuario.getCorreo());
            cv.put(NOMBRE, usuario.getNombre());
            cv.put(APELLIDO, usuario.getApellido());
            cv.put(CONTRASEÑA, usuario.getContraseña());

            long insert = db.insert(USUARIO_TABLE, null, cv);
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public int checkUsuario(String usuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String check = "Select " + ID_USUARIO + ", " + NOMBRE_USUARIO + ", " + CONTRASEÑA + " from " + USUARIO_TABLE + " where " + NOMBRE_USUARIO + "= '" + usuario + "' and " + CONTRASEÑA + "='" + contraseña+ "'";
        Cursor cursor = db.rawQuery(check, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            return id;
        }
        else
            return -1;

    }

    public boolean addPlastic(Plastico plastico) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (plastico != null) {
            cv.put(TIPO, plastico.getTipo());
            cv.put(CANTIDAD, plastico.getCantidad());
            cv.put(TAMAÑO, plastico.getTamaño());
            cv.put(PESO, plastico.getPeso());

            long insert = db.insert(PLASTICOS_TABLE, null, cv);
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
}
