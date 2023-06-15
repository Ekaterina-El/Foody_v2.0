package com.elka.foody.data.meals

import com.elka.foody.domain.meel.Meal

class MealsMapper {
  fun mapEntityToDbModel(item: Meal) = MealDbModel(
    id = item.id,
    name = item.name,
    category = item.category,
    price = item.price,
    image = item.image
  )

  fun mapDbModelToEntity(item: MealDbModel) = Meal(
    id = item.id,
    name = item.name,
    category = item.category,
    price = item.price,
    image = item.image
  )

  fun mapListDbModelsToEntity(list: List<MealDbModel>) = list.map { mapDbModelToEntity(it) }
}