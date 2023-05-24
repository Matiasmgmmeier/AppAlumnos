package com.example.appalumnos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appalumnos.AlumnoAdapter.StudentAdapter
import com.example.appalumnos.Entidades.Student


class ListOfStudentsActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_alumnos)

        val bundle = intent.extras
        val name = bundle?.getString("nombre", "")
        val prefs = getSharedPreferences("References", MODE_PRIVATE)
        val namePreference = prefs.getString("name", "default value")

        if (name == namePreference)
        {
            val listOfStudents= getListOfStudents()
            val intent = Intent(this, DetailActivity::class.java)

            for (item in listOfStudents)
            {
                if (item.Name==name){
                    intent.putExtra("name", item.Name)
                    intent.putExtra("age", item.Age.toString())
                    intent.putExtra("url", item.PictureUrl)
                }

            }

            startActivity(intent)

        }

        recyclerview = findViewById(R.id.ReciclerView)
        recyclerview .layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(applicationContext)
        recyclerview.adapter = adapter

        adapter.submitList(getListOfStudents())
        adapter.onItemClickListener = { student ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", student.Name)
            intent.putExtra("age", student.Age.toString())
            intent.putExtra("url", student.PictureUrl)
            startActivity(intent)
        }


    }

    private fun getListOfStudents(): MutableList<Student>{

        return mutableListOf(
            Student(1,"Lili",20,"data:image/jpeg;base64,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"),
            Student(2,"Richard",22,"data:image/jpeg;base64,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"),
            Student(3,"Lia",40,"data:image/jpeg;base64,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"),
            Student(4,"Pepe",222,"data:image/jpeg;base64,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"),
            Student(6,"Leon",20,"data:image/jpeg;base64,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"),
            Student(7,"Rosa",21,"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAIEAVgMBIgACEQEDEQH/xAAcAAABBAMBAAAAAAAAAAAAAAAAAQMGBwIEBQj/xAA/EAABAwIDBQUEBggHAAAAAAABAAIDBBEFEiEGEyIxQQdRYXGRFIGhsSMyQlJy0RUWM0OC4fDxFyU2YqKywf/EABkBAAMBAQEAAAAAAAAAAAAAAAACAwQBBf/EACARAAMBAAICAgMAAAAAAAAAAAABAhEDITJBEjETIjP/2gAMAwEAAhEDEQA/ALxQhCABCEhNggBUKudte1LD8FlfRYdIyoq26OcOIMPkoR/iXjVYC5tdIxoOrWsa21/LmEjvBlLL9QqHwrtExzD6jPUVzqqB37qdodr+LmPVXHs9jtLjtC2pprtdYZ4nHVhXVSYOWjqoQhMKCEIQAIQhAAoD2kbQ1TCzZ7BHf5hVt+keP3TOpP8AXUKdVEzKenkmlNmRtLnHuAFyqr2Qvi9VW7QTayVsp3V/sxg8NvPn71LlrF0V4YVPs4dJ2XUbo81TVzvmdxOfpqVtx9nUMTSz2pxB62sQrEihs1Zbq6htmr4wVZV7BzwROfTVAlLRcMe211nsji02C10cjcwynJJC46uHVt+vgrNdTZlBNssIFNVMq4Bbe3DgPvDUFCdewqJa6Ldp5o6iCOaF2aORoc0jqCnFFOzqv9rwPdOdcwu0/CdR8bqVrVL1aYaWPAQhCY4CEIQBCu17FDhmxVUGOtJVEQCx1sdT8Bb3rl7Fgw7J4a+GEyPdAw5W2FyRrzTPbzG+TZ+jLTwtnN/MjT/1bux7XSbI4Xujuy6lZa/TRZ+R9mnhXR0hXV0EgFTQNMRP1oJs5HusF1QWlmYHS11DpMArxXR1b8Se6NrHB8ZuMzujvcpBROlGHua8nedCFzSuCzYsxku5io6yYjm6OLh9SQuXtGWVeHngeySNwdle2xC1Z8MxioqopocTfAxp42A/X100tourVw1D8KLKqRskoZq5osCVx9oZLGcns4qfZ8ZmoieGWNzmeOtx6a+qspUTsHjkc2LU8xeGzUc2WTXmw3bfyI+IV6jVV4n1hk5l+2ioQhVJAhCEAV921hv6ptcRqJhb0P8ANaHZhidJW7N09JBK0zUTcksd9WakC/na62e22QnAqOmbzknLrd9mn81W/Zli0eCbYS0tW4MhxBm6DidBIHXb66jzIWd+TNPG2pRdlaMrBkF3HvTFO0mN5dI2w59LJ2pD3xt3Lw1/K5F1rNir2ixEDr9QkzWaZxrB+lad6RJbKdWEdUxtFIKfCql+drQIzZzjYC/itiCKoDx7TIx3gxtgPeoZ2y4pHRbIy0mf6aukbExo52BBcfKwt700r0LdZ2VdsYR+sT92fo7lt78xfmvTmCSulwyAyG72gsJ7y02v8F5r2IpDGZJzzvlHofzXpHZ8WwmA/eGb1TR/Qz8ngmdFCEK5nBCEhKAK87W4TPHhrR0c8/AKkdrKW85fELZWF7rfZP8AdW32uYuPaG0kUgLoQ0ZQdczr/Lh9VHYNn5MQw6pqZ2XLqffWHM2tz9CoTLq20aHSXGkyxcCfLLhFFLJcvdAwvudb5Quo1ubXiWthbAylja0gtyDL5WW+zhCVFW2hpwJ0aLW5kqiO15lS7atxeZHRbiNzbnhAu4G3v+avt1y0+KjGMYHSY3V1EVRGHiODdud3FxBt/wAR6qkT8qSJ3WS2yrcEkDaSlbHoZJdSvQuBOD8IpXN5GMWVCzYRUYJXMgex74I3kxygaeAPdy+SuLs5xOPEtlKNzXgyQt3creocFxQ45OxbtVHRJ0IQrEBuWVsbbuPuXBrv0hWc6x1KzX6OmtcjxeRf0stt73PcXHzv3LAhzuqquNexHT9EGqthaJ1X7RNW1r85DnsdICHG973Iv8VKMNoqemYyCKFrY9AW87hbU8IeHtzHW2vdZaszJmQtZFMWkOBL7a2B5e/kqzKzETdPdZrYNI6jfJh89wYXEMv1F9F29Fo1zY6iaKojaWyaghBM81Q1kTha1yTyAXn3xuHh6EXNrTLFKr2aBoiBdM8hsbRzJOiKOjNFShj+KZ3HK7ndx/qydjpWMqN6+8j2jhcdLeSzO+dnz5Dc3aRpp3FaOHic1tGfm5VSyTUdBHJYSMDmnQhwuCFq0GAQYXXOrcHe+ke/9pE3WKQeLfysujkeZASGgDoOqeaSAbq9JP7IS2vo6cFUyRvEQ13UEpVzBqNRZIp/jH+Q50GgSFwA5JHaDwQGg6804hiCDezSlfEHNsQnGiyHGyNDDTa3cuu4cPf3LOljEM8xaS7fHMO5ugFvW5T5AcLFMPYY9W6gH0KbFX2GtdGzZAWLHlzA7vSpTpkGjnZYPbfksgT3hI7Q3HcgAAvoeiEjTe/mhADb3cA8U4xR/HsW/RdNBNI0mDPxubzb4rug5WjvKZy0hE0OkrC9ysSSlboEo5kBdLYcQshvJHVADVKMsLWnXLonPApuK4aR/uPzThafvfBdZwTL3IzW0d6pLSD7qQuf9punfdADRfkzd2ZKuTjlU6njAaeJ0h9LfzQrLjbWk3eHO20/03OpND+zh/APkhCWvFBP2xwrMIQplEZDkgIQg6NM+p/Gf+ydQhDOAVi/6qEIQEZ2n+tT/wAXyalQhb+LwMnJ5H//2Q=="),
            Student(8,"Carlos",35,"data:image/jpeg;base64,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"),
            Student(9,"Coco",30,"data:image/jpeg;base64,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"),
            Student(10,"Faraday",50,"data:image/jpeg;base64,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"),

        )


    }
}