package ru.gagarin.foodtracker

interface OnInteractionListener {
    fun onEditClicked(food: Food)
    fun onDeleteClicked(food: Food)
}
