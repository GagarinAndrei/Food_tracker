package ru.gagarin.foodtracker.presentation

import ru.gagarin.foodtracker.Food

interface OnInteractionListener {
    fun onEditClicked(food: Food)
    fun onDeleteClicked(food: Food)
}
