package com.elka.foody.domain.meel

class LoadMealsUseCase(private val rep: MealsRepository) {
  fun invoke(onEnd: () -> Unit) {
    return rep.loadMealsByNetwork(onEnd)
  }
}