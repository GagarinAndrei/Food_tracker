package ru.gagarin.foodtracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import ru.gagarin.foodtracker.Food
import ru.gagarin.foodtracker.databinding.FoodDetailsBinding
import java.time.LocalDateTime

class FoodDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FoodDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        val viewModel: FoodViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )
        val newFood = MutableLiveData(
            Food(
                id = 0,
                name = binding.foodName.toString(),
                calories = binding.foodCaloriesValue.toString(),
                carbs = binding.foodCarbsValue.toString(),
                protein = binding.foodProteinValue.toString(),
                fat = binding.foodFatValue.toString(),
//                date = LocalDateTime.now()
            )
        )
        binding.saveButton.setOnClickListener {
//            viewModel.changeContent()
            viewModel.saveFood()
            findNavController().popBackStack()
        }
        return binding.root
    }
}