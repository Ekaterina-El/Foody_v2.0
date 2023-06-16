package com.elka.foody.presentation

import android.app.Application
import android.net.Network
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elka.foody.data.meals.MealsRepositoryImpl
import com.elka.foody.domain.meel.GetCashedMealsUseCase
import com.elka.foody.domain.meel.GetMealsUseCase
import com.elka.foody.domain.meel.LoadMealsUseCase
import kotlinx.coroutines.launch

class MenuViewModel(application: Application) : BaseViewModel(application) {
  private val rep = MealsRepositoryImpl(application)
  private val getMealsUseCase = GetMealsUseCase(rep)
  private val getCashedMealsUseCase = GetCashedMealsUseCase(rep)
  private val loadMealsUseCase = LoadMealsUseCase(rep)

  val cashedMeals = getCashedMealsUseCase.invoke()

  private var loadFromNetwork = false

  private val _networks = MutableLiveData(listOf<Network>())
  fun hasInternet(): Boolean = _networks.value!!.isNotEmpty()

  fun onRegNetwork(network: Network) {
    viewModelScope.launch {
      val items = _networks.value!!.toMutableList()
      items.add(network)
      _networks.value = items

      if (!loadFromNetwork) loadMeals()
    }
  }

  fun onUnregNetwork(network: Network) {
    viewModelScope.launch {
      val items = _networks.value!!.toMutableList()
      items.remove(network)
      _networks.value = items
    }
  }

  fun loadMeals() {
    if (!hasInternet()) {
      loadFromNetwork = false
      return
    }

    val work = Work.LOAD_MEALS
    addWork(work)
    rep.loadMealsByNetwork {
      loadFromNetwork = true
      removeWork(work)
    }
  }
}