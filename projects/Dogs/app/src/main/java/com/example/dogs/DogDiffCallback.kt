package com.example.dogs

import androidx.recyclerview.widget.DiffUtil

class DogDiffCallback(private val oldList: List<Dog>, private val newList: List<Dog>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDog = oldList[oldItemPosition]
        val newDog = newList[newItemPosition]
        return oldDog.name == newDog.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDog = oldList[oldItemPosition]
        val newDog = newList[newItemPosition]
        return oldDog == newDog
    }
}
