package com.example.dodopizza

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
class PizzaDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_details)

        val pizza = intent.getSerializableExtra("pizza") as Pizza
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val pizzaImageView: ImageView = findViewById(R.id.pizzaImageView)
        val pizzaNameTextView: TextView = findViewById(R.id.pizzaNameTextView)
        val pizzaDescriptionTextView: TextView = findViewById(R.id.pizzaDescriptionTextView)
        val pizzaIngredientsTextView: TextView = findViewById(R.id.pizzaIngredientsTextView)
        val pizzaPriceTextView: TextView = findViewById(R.id.pizzaPriceTextView)
        Glide.with(this).load(pizza.imageUrl).placeholder(R.drawable.placeholder_image).into(pizzaImageView)
//        pizzaImageView.setImageResource(pizza.imageResourceId)
        pizzaNameTextView.text = pizza.name
        pizzaDescriptionTextView.text = pizza.description
        pizzaIngredientsTextView.text = pizza.ingredients
        pizzaPriceTextView.text = "Price: $${pizza.price}"
    }
}