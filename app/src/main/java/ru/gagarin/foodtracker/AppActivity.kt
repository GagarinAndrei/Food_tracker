package ru.gagarin.foodtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gagarin.foodtracker.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}