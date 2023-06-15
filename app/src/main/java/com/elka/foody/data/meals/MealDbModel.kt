package com.elka.foody.data.meals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals_table")
data class MealDbModel(
  @PrimaryKey
  val id: String,
  val name: String,
  val category: String,
  val price: Int,
  val image: String
)