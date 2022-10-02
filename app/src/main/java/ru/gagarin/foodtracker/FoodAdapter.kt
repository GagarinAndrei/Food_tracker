package ru.gagarin.foodtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.gagarin.foodtracker.databinding.FoodShortInfoBinding

class FoodAdapter(
    private val interactionListener: OnInteractionListener
) : ListAdapter<Food, FoodViewHolder>(FoodDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding =
            FoodShortInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = getItem(position)
        holder.bind(food)
    }

}

class FoodViewHolder(
    private val binding: FoodShortInfoBinding,
    private val interactionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(food: Food) {
        binding.apply {
            foodName.text = food.name
            foodCalories.text = food.calories.toString()
            menuButton.setOnClickListener {
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
    }
}

class FoodDiffCallback() : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}

