package com.elka.foody.domain.meel

import androidx.lifecycle.LiveData

class GetMealsUseCase(private val rep: MealsRepository) {
  fun invoke(): LiveData<List<Meal>> {
    return rep.getMeals()
  }
}