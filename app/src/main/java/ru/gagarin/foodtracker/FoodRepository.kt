package ru.gagarin.foodtracker

import androidx.lifecycle.LiveData

interface FoodRepository {
    fun getAll(): LiveData<List<Food>>
    fun save(food: Food)
    fun removeById(id: Int)
    fun edit(food: Food)
}