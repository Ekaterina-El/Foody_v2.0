package com.elka.foody.domain.meel

import androidx.lifecycle.LiveData

class LoadMealsUseCase(private val rep: MealsRepository) {
  fun invoke(onEnd: () -> Unit) {
    return rep.loadMeals(onEnd)
  }
}