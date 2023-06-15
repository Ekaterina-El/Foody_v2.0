package com.elka.foody.data.meals

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealsDao {
  @Query("SELECT * FROM meals_table")
  fun getMealsList(): LiveData<List<MealDbModel>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addMeal(meal: MealDbModel)

  @Query("DELETE FROM meals_table")
  fun clearMeals()
}