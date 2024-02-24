package com.example.dodopizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var pizzaRecyclerView: RecyclerView
    private lateinit var adapter: PizzaAdapter
    private lateinit var pizzas: List<Pizza>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView)

        pizzas = listOf(
            Pizza("Margherita",
                "https://dodopizza-a.akamaihd.net/static/Img/Products/a8ae844fcc604bae895d46b2cef9bf9e_584x584.webp",
                "Classic Margherita Pizza",
                "Tomato, mozzarella, basil",
                9.99),
            Pizza("Pepperoni",
                "https://dodopizza-a.akamaihd.net/static/Img/Products/d2c4e3df89ae4e42b08a41981de79171_584x584.webp",
                "Classic Pepperoni Pizza",
                "Tomato, mozzarella, pepperoni",
                11.99),
            Pizza(
                "BBQ Chicken",
                "https://dodopizza-a.akamaihd.net/static/Img/Products/5ff8baa047524864810890dc08cd176e_584x584.webp",
                "BBQ Chicken Pizza",
                "Grilled chicken, BBQ sauce, mozzarella, red onions, cilantro",
                12.99
            ),
            Pizza(
                "Hawaiian",
                "https://dodopizza-a.akamaihd.net/static/Img/Products/7d56c950bcef45b38ae61cca1b27dbf5_584x584.webp",
                "Hawaiian Pizza",
                "Pineapple, ham, mozzarella, tomato sauce",
                10.99
            ),
            Pizza(
                "Vegetarian",
                "https://dodopizza-a.akamaihd.net/static/Img/Products/d7b618fe89cc40adac7f0e74acd68bd7_584x584.webp",
                "Vegetarian Pizza",
                "Mushrooms, bell peppers, black olives, onions, mozzarella, tomato sauce",
                10.99
            ),
            Pizza(
                "Meat Lover's",
            "https://dodopizza-a.akamaihd.net/static/Img/Products/5c9a4f0b3ea441b1aea719a8da11be3f_584x584.webp",
            "Meat Lover's Pizza",
            "Pepperoni, sausage, bacon, ground beef, mozzarella, tomato sauce",
            13.99
        )

        )
        adapter = PizzaAdapter(pizzas)
        pizzaRecyclerView.layoutManager = LinearLayoutManager(this)
        pizzaRecyclerView.adapter = adapter
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            searchPizzas(query)
        }
    }
    private fun searchPizzas(query: String) {
        val results = pizzas.filter { it.name.contains(query, true) || it.ingredients.contains(query, true) }
        adapter.submitList(results)
    }
}