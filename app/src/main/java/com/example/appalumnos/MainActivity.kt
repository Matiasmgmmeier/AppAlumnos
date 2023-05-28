package com.example.appalumnos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var continueButton: Button
    private lateinit var editTextNombre: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        continueButton = findViewById(R.id.BotonContinuar)
        editTextNombre = findViewById(R.id.TextBox)

        val userName = "richard"
        val preferences = getSharedPreferences("References", MODE_PRIVATE)
        val edit = preferences.edit().putString("name", userName)
        edit.apply()

        val name = preferences.getString("name", "")

        continueButton.setOnClickListener {

            if (name != null) {

                val nombreAux = editTextNombre.text.toString()
                val intent = Intent(this, ListOfStudentsActivity::class.java)
                intent.putExtra("nombre", nombreAux)
                startActivity(intent)

            }
        }


    }
}