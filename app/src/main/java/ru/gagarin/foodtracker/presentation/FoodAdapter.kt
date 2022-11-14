package ru.gagarin.foodtracker.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.gagarin.foodtracker.Food
import ru.gagarin.foodtracker.R
import ru.gagarin.foodtracker.databinding.FoodShortInfoBinding
import kotlin.properties.Delegates

class FoodAdapter(
    private val interactionListener: OnInteractionListener
) : ListAdapter<Food, FoodViewHolder>(FoodDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding =
            FoodShortInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FoodViewHolder(
    private val binding: FoodShortInfoBinding,
    private val interactionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var food: Food

    init {
        binding.menuButton.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.food_option_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.remove_food -> {
                            interactionListener.onDeleteClicked(food)
                            true
                        }
                        R.id.edit_food -> {
                            interactionListener.onEditClicked(food)
                            true
                        }
                        else -> false
                    }
                }
            }.show()
        }
    }

    fun bind(food: Food) {
        binding.foodName.text = food.name
        binding.foodCalories.text = food.calories
//            binding.menuButton.setOnClickListener {
//                PopupMenu(it.context, it).apply {
//                    inflate(R.menu.food_option_menu)
//                    setOnMenuItemClickListener { item ->
//                        when (item.itemId) {
//                            R.id.remove_food -> {
//                                interactionListener.onDeleteClicked(food)
//                                true
//                            }
//                            R.id.edit_food -> {
//                                interactionListener.onEditClicked(food)
//                                true
//                            }
//                            else -> false
//                        }
//                    }
//                }.show()
//            }
    }
}

private object FoodDiffCallback : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem == newItem
}

