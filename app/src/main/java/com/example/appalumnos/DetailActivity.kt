package com.example.appalumnos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var picture: ImageView
    private lateinit var textViewAge: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

      picture= findViewById(R.id.DetailPicture)
      textViewName = findViewById(R.id.textViewDetailName)
      textViewAge = findViewById(R.id.textViewDetailAge)

        val bundle =intent.extras
        val pictureUrl = bundle?.getString("url") ?: ""
        val name = bundle?.getString("name", "")
        val age = bundle?.getString("age","" )

        textViewName.text = "Name : "+ name
        textViewAge.text= "Age : "+ age

        Glide.with(applicationContext)
            .load(pictureUrl)
            .into(picture)

    }
}