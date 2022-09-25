package ru.gagarin.foodtracker.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gagarin.foodtracker.MainFragment
import ru.gagarin.foodtracker.R

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commit()
    }

}