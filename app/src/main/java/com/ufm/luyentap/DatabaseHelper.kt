package com.ufm.luyentap

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "nhanviens.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "nhanvien"
        const val COLUMN_ID = "id"
        const val COLUMN_MSNV = "msnv"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_PHONG = "department"
        const val COLUMN_CHUCVU = "chucvu"
        const val COLUMN_BIRTHDAY = "birthday"
        const val COLUMN_NGAYVAOLAM = "ngayvaolam"
        const val COLUMN_SALARY = "salary"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_SQL = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_MSNV TEXT UNIQUE," +  // Ensure this column is of TEXT type
                "$COLUMN_NAME TEXT NOT NULL," +
                "$COLUMN_PHONE TEXT," +
                "$COLUMN_PHONG TEXT," +
                "$COLUMN_CHUCVU TEXT," +
                "$COLUMN_BIRTHDAY TEXT," +  // Store dates as TEXT to avoid issues
                "$COLUMN_NGAYVAOLAM TEXT," +  // Store dates as TEXT to avoid issues
                "$COLUMN_SALARY REAL)"
        db?.execSQL(CREATE_TABLE_SQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}