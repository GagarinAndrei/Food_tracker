package ru.gagarin.foodtracker.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.gagarin.foodtracker.Food
import ru.gagarin.foodtracker.data.FoodRepository
import ru.gagarin.foodtracker.data.FoodRepositoryInFileImplementation

private val empty = Food(
    id = 0,
    name = "",
    calories = "",
    carbs = "",
    protein = "",
    fat = "",
//    date = LocalDateTime.now()
)

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FoodRepository = FoodRepositoryInFileImplementation(application)
    val data = repository::data
    private val currentFood = MutableLiveData<Food?>(null)

    fun saveFood() {
        currentFood.value?.let {
            repository.save(it)
        }
        currentFood.value = empty
    }

    fun editFood(food: Food) {
        currentFood.value = food
    }

//    fun changeContent() {
////        val text = content.trim()
////        if (edited.value?.content == text) {
////            return
////        }
//        edited.value = edited.value?.copy(
//            id = id,
//            name = food.name,
//            calories = food.calories,
//            carbs = food.carbs,
//            protein = food.protein,
//            fat = food.fat,
//            date = LocalDateTime.now()
//        )
//    }

    fun removeFoodById(id: Int) = repository.removeById(id)
}