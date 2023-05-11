package com.loki.data.mappers

import com.loki.data.dto.DrinkDto
import com.loki.domain.models.Drink


fun DrinkDto.toDrink(): Drink {
    return Drink(
        idDrink, strDrink, strDrinkThumb
    )
}