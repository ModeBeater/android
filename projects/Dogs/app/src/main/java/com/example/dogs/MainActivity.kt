package com.example.dogs

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DogAdapter
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private var dogsList: MutableList<Dog> = ArrayList() // Changed to MutableList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DogAdapter(dogsList)
        recyclerView.adapter = adapter

        // Set up search functionality
        setSearchFunctionality()
    }

    private fun fetchDataFromApi(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DogApiService::class.java)
        val apiKey = "vWnevi2N38nwIMVoaE/zbQ==OxrQZRqCz3mjj8Vs"
        val call = service.getDogs(query, apiKey)

        call.enqueue(object : Callback<List<Dog>> {
            override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {
                if (response.isSuccessful) {
                    val dogs = response.body()
                    Log.d(TAG, "Response: $dogs")
                    dogs?.let {
                        dogsList.clear()
                        dogsList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e(TAG, "Error: ${response.code()} ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                Log.e(TAG, "Error fetching data: ${t.message}")
            }
        })
    }



    private fun setSearchFunctionality() {
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            fetchDataFromApi(query)
        }
    }

    private fun filterData(query: String) {
        val filteredList = dogsList.filter { dog -> dog.name.contains(query, ignoreCase = true) }
        adapter.updateData(filteredList)
    }
}

