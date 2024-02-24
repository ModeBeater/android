package com.example.dodopizza

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import java.io.Serializable

class PizzaAdapter(private var pizzas: List<Pizza>) : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.pizza_item, parent, false)
        return PizzaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = pizzas[position]
        holder.bind(pizza)
    }

    override fun getItemCount(): Int {
        return pizzas.size
    }

    fun submitList(newList: List<Pizza>) {
        pizzas = newList
        notifyDataSetChanged()
    }

    inner class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.pizzaNameTextView)
        private val ingredientsTextView: TextView = itemView.findViewById(R.id.pizzaIngredientsTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.pizzaImageView)
        private val price: TextView = itemView.findViewById(R.id.pizzaPriceTextView)
        fun bind(pizza: Pizza) {
            nameTextView.text = pizza.name
            ingredientsTextView.text = pizza.ingredients
            price.text = "Price: $${pizza.price}"
            Glide.with(itemView.context)
                .load(pizza.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)
            itemView.setOnClickListener {
                // Handle pizza item click
                val intent = Intent(itemView.context, PizzaDetailsActivity::class.java)
                intent.putExtra("pizza", pizza)
                itemView.context.startActivity(intent)
            }
        }
    }
}