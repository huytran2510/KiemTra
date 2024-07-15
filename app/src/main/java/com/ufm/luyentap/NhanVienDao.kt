package com.ufm.luyentap

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.util.Date

class NhanVienDao(private var db: SQLiteDatabase) {
    fun add(msnv: String, name: String, chucvu: String, phai: String, department: String, ngaysinh: Date, ngayvaolam: Date, salary: Float) {
        try {
            if (msnv.isNotEmpty() && name.isNotEmpty()) {
                val contentValues = ContentValues().apply {
                    put(DatabaseHelper.COLUMN_MSNV, msnv)
                    put(DatabaseHelper.COLUMN_NAME, name)
                    put(DatabaseHelper.COLUMN_PHONG, department)
                    put(DatabaseHelper.COLUMN_CHUCVU, chucvu)
                    put(DatabaseHelper.COLUMN_SALARY, salary)
                    put(DatabaseHelper.COLUMN_BIRTHDAY, ngaysinh.toString()) // Consider formatting the date
                    put(DatabaseHelper.COLUMN_NGAYVAOLAM, ngayvaolam.toString()) // Consider formatting the date
                }
                db.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
                Log.i("Add employee", "Employee added successfully: $name")
            } else {
                if (msnv.isEmpty()) {
                    Log.e("Add employee", "Failed to add employee: msnv is empty")
                }
                if (name.isEmpty()) {
                    Log.e("Add employee", "Failed to add employee: name is empty")
                }
            }
        } catch (e: Exception) {
            Log.e("Add employee", "Failed to add employee", e)
            throw Exception("Fail to insert field: ", e)
        }
    }



}