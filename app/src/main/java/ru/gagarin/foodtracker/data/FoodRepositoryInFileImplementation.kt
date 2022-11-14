package ru.gagarin.foodtracker.data

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.gagarin.foodtracker.Food
import kotlin.properties.Delegates

class FoodRepositoryInFileImplementation(
    private val application: Application
) : FoodRepository {
    private val prefs = application.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Food::class.java).type
    private var nextId by Delegates.observable(
        prefs.getInt(PREFS_NEXT_ID_KEY, 0)
    ) { _, _, newValue ->
        prefs.edit { putInt(PREFS_NEXT_ID_KEY, newValue) }
    }
    override val data = MutableLiveData(List<Food>)

    private var foods
        get() = checkNotNull(data.value) {
            "Data value should be not null"
        }
        set(value) {
            application.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use {
                it.write(gson.toJson(value))
            }
            data.value = value
        }
//    val data: MutableLiveData(List<Food>)
    init {
        val foodsFile = application.filesDir.resolve(FILE_NAME)
        val foods: List<Food> = if (foodsFile.exists()) {
            application.openFileInput(FILE_NAME).bufferedReader().use {
                gson.fromJson(it, type)
//                data.value = foods
            }
        } else emptyList()
        data = MutableLiveData(foods)
//            sync()
    }


//    private fun sync() {
//        application.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use {
//            it.write(gson.toJson(foods))
//        }
//    }

    override fun getFoodsData(): LiveData<List<Food>> = data

    override fun save(food: Food) {
        if (food.id == 0) {
            foods = listOf(
                food.copy(
                    id = nextId++,
                    name = "",
                    calories = "",
                    carbs = "",
                    protein = "",
                    fat = "",
//                    date = LocalDateTime.now()
                )
            ) + foods
            data.value = foods
//            sync()
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
//                    date = food.date
                )
            }
        }
        data.value = foods
//        sync()
    }

    override fun removeById(id: Int) {
        foods = foods.filter {
            it.id != id
        }
        data.value = foods
//        sync()
    }

    override fun edit(food: Food) {
        TODO("Not yet implemented")
    }

    private companion object {
        const val PREFS_NEXT_ID_KEY = "nextId"
        const val FILE_NAME = "foods.json"
    }
}
