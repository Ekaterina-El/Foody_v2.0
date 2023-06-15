package com.elka.foody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elka.foody.data.meals.MealsRepositoryImpl

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onResume() {
    super.onResume()

    val rep = MealsRepositoryImpl
    rep.loadMeals {
      val items = rep.getMeals().value
    }
  }
}