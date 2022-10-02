package ru.gagarin.foodtracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

class FoodRepositoryInFileImplementation(
    private val context: Context
) : FoodRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Food::class.java).type
    private val filename = "foods.json"
    private var nextId = 1
    private var foods = emptyList<Food>()
    private val data = MutableLiveData(foods)

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                foods = gson.fromJson(it, type)
                data.value = foods
            }
        } else {
            sync()
        }
    }

    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(foods))
        }
    }

    override fun getAll(): LiveData<List<Food>> = data

    override fun save(food: Food) {
        if (food.id == 0) {
            foods = listOf(
                food.copy(
                    id = nextId++,
                    name = "",
                    calories = 0,
                    carbs = 0,
                    protein = 0,
                    fat = 0,
                    date = LocalDateTime.now()
                )
            ) + foods
            data.value = foods
            sync()
            return
        }
        foods = foods.map {
            if (it.id != food.id) {
                it
            } else {
                it.copy(
                    name = food.name,
                    calories = food.calories,
                    carbs = food.carbs,
                    protein = food.protein,
                    fat = food.fat,
                    date = food.date
                )
            }
        }
        data.value = foods
        sync()

    }

    override fun removeById(id: Int) {
        foods = foods.filter {
            it.id != id
        }
        data.value = foods
        sync()
    }

    override fun edit(food: Food) {
        TODO("Not yet implemented")
    }

}
