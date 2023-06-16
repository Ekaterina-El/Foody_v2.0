package com.elka.foody.domain.meel

import androidx.lifecycle.LiveData

class GetCashedMealsUseCase(private val rep: MealsRepository) {
  fun invoke(): LiveData<List<Meal>> {
    return rep.getCashedMeals()
  }
}