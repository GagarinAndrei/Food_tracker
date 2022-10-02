package ru.gagarin.foodtracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime

private val empty = Food(
    id = 0,
    name = "",
    calories = 0,
    carbs = 0,
    protein = 0,
    fat = 0,
    date = LocalDateTime.now()
)

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FoodRepository = FoodRepositoryInFileImplementation(application)
    val data = repository.getAll()
    private val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(food: Food) {
        edited.value = food
    }

    fun removeById(id: Int) = repository.removeById(id)
}