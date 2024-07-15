package com.ufm.luyentap

import android.app.DatePickerDialog
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ufm.luyentap.ui.theme.LuyenTapTheme
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var list: MutableList<String>
    private lateinit var nhanVienDao: NhanVienDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase
        nhanVienDao = NhanVienDao(db)

        val msnv: EditText = findViewById<EditText?>(R.id.msnv)
        val name: EditText = findViewById(R.id.name)
        val phone: EditText = findViewById(R.id.phone)
        val chucvu: EditText = findViewById(R.id.chucvu)
        val department: EditText = findViewById(R.id.department)
        val ngaysinh: TextView = findViewById(R.id.edtNgaysinh)
        val ngayvaolam: TextView = findViewById(R.id.edtNgayvaolam)
        val salary: EditText = findViewById(R.id.edtSalary)
        val btnThem: Button = findViewById(R.id.btnThem)
        val btnNgaysinh: Button = findViewById(R.id.btnNgaysinh)
        val btnNgayvaolam: Button = findViewById(R.id.btnNgayvaolam)
        val btnTinhluong: Button = findViewById(R.id.btnTinhluong)



        btnNgaysinh.setOnClickListener {
            showCalendar(ngaysinh)
        }

        btnNgayvaolam.setOnClickListener {
            showCalendar(ngayvaolam)
        }

        btnThem.setOnClickListener {
            nhanVienDao.add(msnv.text.toString(), name.text.toString(), chucvu.text.toString(), department.text.toString()
            ,department.text.toString(), convertStringtoDate(ngaysinh.text.toString()), convertStringtoDate(ngayvaolam.text.toString()) , salary.text.toString().toFloat() )
        }
    }

    fun convertStringtoDate(s: String): Date {
        val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
        val date = dateFormat.parse(s)
        return date
    }

    private fun showCalendar(ngay: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                ngay.text = date
                Toast.makeText(this, "Selected date: $date", Toast.LENGTH_SHORT).show()
            }, year, month, day)
        datePickerDialog.show()
    }
}