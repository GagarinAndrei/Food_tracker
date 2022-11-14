package ru.gagarin.foodtracker.data

import androidx.lifecycle.LiveData
import ru.gagarin.foodtracker.Food

interface FoodRepository {
    val data: LiveData<List<Food>>
    fun getFoodsData(): LiveData<List<Food>>
    fun save(food: Food)
    fun removeById(id: Int)
    fun edit(food: Food)
}