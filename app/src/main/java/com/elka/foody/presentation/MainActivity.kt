package com.elka.foody.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elka.foody.R
import com.elka.foody.data.meals.MealsRepositoryImpl

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()

    val rep = MealsRepositoryImpl(application)


    rep.getMeals().observe(this) {
      val items = 1
    }
    rep.loadMealsByNetwork {}

    rep.getCashedMeals().observe(this) {
      val items = it
    }
  }
}