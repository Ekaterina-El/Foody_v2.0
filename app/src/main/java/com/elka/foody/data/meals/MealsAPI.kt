package com.elka.foody.data.meals

import com.elka.foody.data.HttpConnector
import com.google.gson.Gson

class MealsAPI(private val connector: HttpConnector) {

  fun getAll(onError: ((Throwable) -> Unit) = {}, onSuccess: (MealsFeed) -> Unit) {
    val a = connector.createRequest(API_URL).map { Gson().fromJson(it, MealsFeed::class.java) }
      .subscribe({ feed -> onSuccess(feed) }) { error ->
        onError(error)
      }
  }

  companion object {
    private const val API_URL = "https://foody.free.beeceptor.com/menu"
    private val LOCK = Any()

    private var INSTANCE: MealsAPI? = null
    fun getInstance(connector: HttpConnector): MealsAPI? {
      if (INSTANCE != null) return INSTANCE

      synchronized(LOCK) {
        if (INSTANCE != null) return INSTANCE

        val api = MealsAPI(connector)
        INSTANCE = api
        return api
      }
    }
  }
}