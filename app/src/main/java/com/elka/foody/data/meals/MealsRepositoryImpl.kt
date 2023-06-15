package com.elka.foody.data.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elka.foody.data.HttpConnector
import com.elka.foody.domain.meel.Meal
import com.elka.foody.domain.meel.MealsRepository

object MealsRepositoryImpl: MealsRepository {
  private val connector = HttpConnector
  private val api = MealsAPI(connector)
  private val meals = MutableLiveData<List<Meal>>(listOf())

  override fun getMeals(): LiveData<List<Meal>> {
    return meals
  }

  override fun loadMeals(onEnd: () -> Unit) {
    api.getAll({ onEnd() }) {
      meals.value = it.meals
      onEnd()
    }
  }
}