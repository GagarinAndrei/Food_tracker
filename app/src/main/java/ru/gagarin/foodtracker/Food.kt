package ru.gagarin.foodtracker

import java.time.LocalDateTime

data class Food(
    val id: Int,
    val name: String,
    val calories: String,
    val carbs: String,
    val protein: String,
    val fat: String,
//    val date: LocalDateTime
) {

}