package ru.gagarin.foodtracker.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gagarin.foodtracker.MainScreenFragment
import ru.gagarin.foodtracker.R
import ru.gagarin.foodtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MainScreenFragment())
                .commit()
    }


}