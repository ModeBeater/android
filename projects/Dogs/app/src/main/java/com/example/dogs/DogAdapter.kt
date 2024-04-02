package com.example.dogs

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DogAdapter(private var dogs: List<Dog>) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dog_item, parent, false)
        return DogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogs[position]
        holder.bind(dog)
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    fun updateData(newDogs: List<Dog>) {
        val diffCallback = DogDiffCallback(dogs, newDogs)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dogs = newDogs
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val sheddingTextView: TextView = itemView.findViewById(R.id.sheddingTextView)
        private val barkingTextView: TextView = itemView.findViewById(R.id.barkingTextView)
        private val energyTextView: TextView = itemView.findViewById(R.id.energyTextView)
        private val protectivenessTextView: TextView = itemView.findViewById(R.id.protectivenessTextView)
        private val trainabilityTextView: TextView = itemView.findViewById(R.id.trainabilityTextView)

        fun bind(dog: Dog) {
            nameTextView.text = dog.name
            Glide.with(itemView.context).load(dog.image_link).into(imageView)
//            Log.d(TAG, "Image URL: ${dog.image_link}")
            sheddingTextView.text = "Shedding: ${getSheddingDescription(dog.shedding)}"
            barkingTextView.text = "Barking: ${getBarkingDescription(dog.barking)}"
            energyTextView.text = "Energy: ${getEnergyDescription(dog.energy)}"
            protectivenessTextView.text = "Protectiveness: ${getProtectivenessDescription(dog.protectiveness)}"
            trainabilityTextView.text = "Trainability: ${getTrainabilityDescription(dog.trainability)}"
        }

        private fun getSheddingDescription(shedding: Int): String {
            return when (shedding) {
                1 -> "No shedding"
                2 -> "Low shedding"
                3 -> "Moderate shedding"
                4 -> "High shedding"
                5 -> "Maximum shedding"
                else -> "Unknown"
            }
        }

        private fun getBarkingDescription(barking: Int): String {
            return when (barking) {
                1 -> "Minimal barking"
                2 -> "Occasional barking"
                3 -> "Moderate barking"
                4 -> "Frequent barking"
                5 -> "Excessive barking"
                else -> "Unknown"
            }
        }

        private fun getEnergyDescription(energy: Int): String {
            return when (energy) {
                1 -> "Low energy"
                2 -> "Moderate energy"
                3 -> "Average energy"
                4 -> "High energy"
                5 -> "Very high energy"
                else -> "Unknown"
            }
        }

        private fun getProtectivenessDescription(protectiveness: Int): String {
            return when (protectiveness) {
                1 -> "Minimal alerting"
                2 -> "Occasional alerting"
                3 -> "Moderate alerting"
                4 -> "Frequent alerting"
                5 -> "Maximum alerting"
                else -> "Unknown"
            }
        }

        private fun getTrainabilityDescription(trainability: Int): String {
            return when (trainability) {
                1 -> "Very difficult to train"
                2 -> "Difficult to train"
                3 -> "Average trainability"
                4 -> "Easy to train"
                5 -> "Very easy to train"
                else -> "Unknown"
            }
        }
    }

}
