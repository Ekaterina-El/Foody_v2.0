package com.elka.foody.data.meals

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.elka.foody.data.HttpConnector
import com.elka.foody.domain.meel.Meal
import com.elka.foody.domain.meel.MealsRepository

class MealsRepositoryImpl(application: Application) : MealsRepository {
  private val mealsDao = MealsDB.getInstance(application).getMealsDao()
  private val mapper = MealsMapper()

  private val connector = HttpConnector
  private val api = MealsAPI(connector)
  private val meals = MutableLiveData<List<Meal>>(listOf())

  override fun getMeals(): LiveData<List<Meal>> {
    return meals
  }

  override fun loadMealsByNetwork(onEnd: () -> Unit) {
    api.getAll({ onEnd() }) {
      val mealsItems = it.meals
      meals.value = mealsItems
      saveToDb(mealsItems)
      onEnd()
    }
  }

  private fun saveToDb(mealsItems: List<Meal>) {
    mealsDao.clearMeals()
    mealsItems.forEach { meal ->
      val mealDB = mapper.mapEntityToDbModel(meal)
      mealsDao.addMeal(mealDB)
    }
  }

  override fun getCashedMeals(): LiveData<List<Meal>> {
    return mealsDao.getMealsList().map { mapper.mapListDbModelsToEntity(it) }
  }
}