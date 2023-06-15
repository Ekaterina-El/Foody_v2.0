package com.elka.foody.domain.meel

import androidx.lifecycle.LiveData

interface MealsRepository {
  fun getMeals(): LiveData<List<Meal>>
  fun loadMealsByNetwork(onEnd: () -> Unit)
  fun getCashedMeals(): LiveData<List<Meal>>
}