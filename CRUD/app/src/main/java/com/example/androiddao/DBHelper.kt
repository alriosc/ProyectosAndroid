package com.example.androiddao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "MiniMarketDB", null, 3) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de usuarios con roles
        val createTableUsuarios = """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                nombre TEXT, 
                apellidos TEXT, 
                usuario TEXT, 
                contraseña TEXT, 
                rol TEXT  -- Agregamos el rol (admin, cliente, productor)
            )
        """.trimIndent()
        db.execSQL(createTableUsuarios)

        // Crear tabla de productos
        val createTableProductos = """
        CREATE TABLE productos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT,
            descripcion TEXT,  -- Añadir la columna descripcion
            precio REAL,
            stock INTEGER
        )
        """.trimIndent()
        db.execSQL(createTableProductos)

        // Insertar un usuario administrador por defecto
        val insertAdmin = """
            INSERT INTO usuarios (nombre, apellidos, usuario, contraseña, rol)
            VALUES ('Admin', 'Admin', 'user', '123', 'admin')
        """
        db.execSQL(insertAdmin)

        // Insertar un usuario productor por defecto
        val insertProductor = """
            INSERT INTO usuarios (nombre, apellidos, usuario, contraseña, rol)
            VALUES ('Productor', 'Productor', 'productor', '123', 'productor')
        """
        db.execSQL(insertProductor)

        // Insertar un usuario cliente por defecto
        val insertCliente = """
            INSERT INTO usuarios (nombre, apellidos, usuario, contraseña, rol)
            VALUES ('Cliente', 'Cliente', 'cliente', '123', 'cliente')
        """
        db.execSQL(insertCliente)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS productos")
        onCreate(db)
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE productos ADD COLUMN descripcion TEXT")
        }
        if (oldVersion < 2) {
            // Añadir la columna rol si no existe
            db.execSQL("ALTER TABLE usuarios ADD COLUMN rol TEXT")
        }
    }
    // Retornar la base de datos para operaciones de escritura
    fun getWritable(): SQLiteDatabase {
        return this.writableDatabase
    }

    // Retornar la base de datos para operaciones de lectura
    fun getReadable(): SQLiteDatabase {
        return this.readableDatabase
    }
}


