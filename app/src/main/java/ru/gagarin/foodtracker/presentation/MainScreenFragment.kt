package ru.gagarin.foodtracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.gagarin.foodtracker.Food
import ru.gagarin.foodtracker.R
import ru.gagarin.foodtracker.databinding.FragmentMainScreenBinding


class MainScreenFragment : Fragment() {

    private val viewModel: FoodViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val adapter = FoodAdapter(object : OnInteractionListener {
            override fun onEditClicked(food: Food) {
                viewModel.editFood(food)
            }

            override fun onDeleteClicked(food: Food) {
                viewModel.removeFoodById(food.id)
            }
        })
        binding.foodList.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { foods ->
            adapter.submitList(foods)
        }
        binding.foodAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_foodDetailsFragment)
        }
        return binding.root
    }
}