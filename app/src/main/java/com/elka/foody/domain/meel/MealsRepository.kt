package com.elka.foody.domain.meel

import androidx.lifecycle.LiveData

interface MealsRepository {
  fun getMeals(): LiveData<List<Meal>>
  fun loadMeals(onEnd: () -> Unit)
}