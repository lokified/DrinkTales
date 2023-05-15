package com.loki.data.mappers

import com.loki.data.dto.DrinkDetailDto
import com.loki.data.dto.DrinkDto
import com.loki.domain.models.Drink
import com.loki.domain.models.DrinkDetail


fun DrinkDto.toDrink(): Drink {
    return Drink(
        idDrink, strDrink, strDrinkThumb
    )
}

fun DrinkDetailDto.toDrinkDetail(): DrinkDetail {
    return DrinkDetail(
        dateModified = dateModified,
        idDrink = idDrink,
        strAlcoholic = strAlcoholic,
        strCategory = strCategory,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        strDrink = strDrink,
        strDrinkAlternate = strDrinkAlternate,
        strDrinkThumb = strDrinkThumb,
        strGlass = strGlass,
        strIBA = strIBA,
        strImageAttribution = strImageAttribution,
        strImageSource = strImageSource,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strInstructions = strInstructions,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4
    )
}