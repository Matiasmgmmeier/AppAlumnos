package com.example.appalumnos.AlumnoAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appalumnos.Entidades.Student
import com.example.appalumnos.R

class StudentAdapter(val context: Context) : ListAdapter<Student, StudentAdapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Student) -> Unit

    inner class ViewHolder( private val view: View) : RecyclerView.ViewHolder(view) {

        private val id : TextView = view.findViewById(R.id.textViewIdItem)
        private val name: TextView = view.findViewById(R.id.textViewNombreItem)
        private val picture: ImageView = view.findViewById(R.id.FotoItem)


        fun bind (student: Student) {
            id.text = "ID Student:  "+student.Id.toString()
            name.text= "Name:  "+ student.Name
            picture.setImageResource(R.drawable.logo_nash)

            Glide.with(context)
                .load(student.PictureUrl)
                .into(picture)

              view.setOnClickListener{
                  onItemClickListener(student)
              }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.alumnoitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return  oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }
}