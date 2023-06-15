package com.elka.foody.data.meals

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MealDbModel::class], version = 1, exportSchema = false)
abstract class MealsDB : RoomDatabase() {
  abstract fun getMealsDao(): MealsDao

  companion object {
    private var INSTANCE: MealsDB? = null
    private val LOCK = Any()
    private const val DB_NAME = "mealsdb"

    fun getInstance(application: Application): MealsDB {
      INSTANCE?.let { return it }

      synchronized(LOCK) {
        INSTANCE?.let { return it }

        val db = Room.databaseBuilder(application, MealsDB::class.java, DB_NAME)
          .allowMainThreadQueries().build()
        INSTANCE = db
        return db
      }
    }
  }
}