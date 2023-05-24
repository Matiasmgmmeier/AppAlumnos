package com.example.appalumnos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var botonContinuar: Button
    private lateinit var EditTextNombre: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonContinuar = findViewById(R.id.BotonContinuar)
        EditTextNombre = findViewById(R.id.TextBox)

        val userName = "Richard"
        val preferences = getSharedPreferences("References", MODE_PRIVATE)
        val edit = preferences.edit().putString("name", userName)
        edit.apply()

        val name = preferences.getString("name", "")

        botonContinuar.setOnClickListener {

            if (name != null) {

                val nombreAux = EditTextNombre.text.toString()
                val intent = Intent(this, ListOfStudentsActivity::class.java)
                intent.putExtra("nombre", nombreAux)
                startActivity(intent)

            }
        }


    }
}