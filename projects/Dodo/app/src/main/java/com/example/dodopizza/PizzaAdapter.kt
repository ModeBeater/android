package com.example.dodopizza

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PizzaAdapter(private var pizzas: List<Pizza>) : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.activity_pizza_details, parent, false)
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
        private val descriptionTextView: TextView = itemView.findViewById(R.id.pizzaDescriptionTextView)
//        private val imageView: ImageView = itemView.findViewById(R.id.pizzaImageView)

        fun bind(pizza: Pizza) {
            nameTextView.text = pizza.name
            descriptionTextView.text = pizza.description
//            imageView.setImageResource(pizza.imageResourceId)

            itemView.setOnClickListener {
                // Handle pizza item click
                val intent = Intent(itemView.context, PizzaDetailsActivity::class.java)
                intent.putExtra("pizza", pizza)
                itemView.context.startActivity(intent)
            }
        }
    }
}

private fun Intent.putExtra(key: String, pizza: Pizza) {
    this.putExtra(key, pizza)
}
