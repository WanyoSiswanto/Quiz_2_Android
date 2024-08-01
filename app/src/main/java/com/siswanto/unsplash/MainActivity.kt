package com.siswanto.unsplash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.siswanto.unsplash.adapter.PhotoAdapter
import com.siswanto.unsplash.model.Photo
import com.siswanto.unsplash.network.RetrofitClient
import com.siswanto.unsplash.network.UnsplashApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchPhotos()
    }

    private fun fetchPhotos() {
        val api = RetrofitClient.instance.create(UnsplashApi::class.java)
        api.getPhotos(1, 20).enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        photoAdapter = PhotoAdapter(it)
                        recyclerView.adapter = photoAdapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load photos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}