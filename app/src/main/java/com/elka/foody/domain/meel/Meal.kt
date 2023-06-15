package com.elka.foody.domain.meel

data class Meal(
  val id: String,
  val name: String,
  val category: String,
  val price: Int,
  val image: String
)