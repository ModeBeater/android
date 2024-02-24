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

class PizzaAdapter(private var pizzas: List<Pizza>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_REGULAR_PIZZA = 0
        private const val VIEW_TYPE_NEW_PIZZA = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_REGULAR_PIZZA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.pizza_item, parent, false)
                RegularPizzaViewHolder(view)
            }
            VIEW_TYPE_NEW_PIZZA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.pizza_item_new, parent, false)
                NewPizzaViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pizza = pizzas[position]
        when (getItemViewType(position)) {
            VIEW_TYPE_REGULAR_PIZZA -> (holder as RegularPizzaViewHolder).bind(pizza)
            VIEW_TYPE_NEW_PIZZA -> (holder as NewPizzaViewHolder).bind(pizza)
        }
    }

    override fun getItemCount(): Int {
        return pizzas.size
    }
    override fun getItemViewType(position: Int): Int {
        return if (position < 3) VIEW_TYPE_NEW_PIZZA else VIEW_TYPE_REGULAR_PIZZA
    }
    fun submitList(newList: List<Pizza>) {
        pizzas = newList
        notifyDataSetChanged()
    }
    inner class NewPizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                val intent = Intent(itemView.context, NewPizzaDetailsActivity::class.java)
                intent.putExtra("newPizza", pizza)
                itemView.context.startActivity(intent)
            }
        }
    }
    inner class RegularPizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                val intent = Intent(itemView.context, PizzaDetailsActivity::class.java)
                intent.putExtra("pizza", pizza)
                itemView.context.startActivity(intent)
            }
        }
    }
}