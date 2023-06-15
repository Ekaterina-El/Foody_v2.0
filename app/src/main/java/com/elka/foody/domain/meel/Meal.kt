package com.elka.foody.domain.meel

import com.google.gson.annotations.SerializedName

data class Meal(
  val id: String,
  val name: String,

  @SerializedName("сategory")
  val category: String,
  val price: Int,
  val image: String
)