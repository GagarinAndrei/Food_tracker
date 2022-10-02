package ru.gagarin.foodtracker

import java.time.LocalDateTime

data class Food(
    val id: Int,
    val name: String,
    val calories: Int,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val date: LocalDateTime
) {

}