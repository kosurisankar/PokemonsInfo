package com.skworks.pokeinfo.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.skworks.pokeinfo.model.ApiResource
import com.skworks.pokeinfo.model.NamedApiResource
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.RoundingMode

internal class ApiResourceAdapter : JsonDeserializer<ApiResource> {

    data class Json(val url: String)

    override fun deserialize(
        element: JsonElement,
        type: Type,
        context: JsonDeserializationContext
    ): ApiResource {
        val temp = context.deserialize<Json>(element, com.google.gson.reflect.TypeToken.get(Json::class.java).type)
        return ApiResource(temp.url)
    }
}

internal class NamedApiResourceAdapter : JsonDeserializer<NamedApiResource> {

    data class Json(val name: String, val url: String)

    override fun deserialize(
        element: JsonElement,
        type: Type,
        context: JsonDeserializationContext
    ): NamedApiResource {
        val temp = context.deserialize<Json>(element, TypeToken.get(Json::class.java).type)
        return NamedApiResource(temp.name, temp.url)
    }
}

internal class ChangeColor {
    fun getDarkerColor(color: Color, factor: Float = 0.8f): Color {
        val argb = color.toArgb()
        val red = (argb shr 16 and 0xFF) * factor
        val green = (argb shr 8 and 0xFF) * factor
        val blue = (argb and 0xFF) * factor
        return Color(
            red = red.coerceIn(0f, 255f) / 255f,
            green = green.coerceIn(0f, 255f) / 255f,
            blue = blue.coerceIn(0f, 255f) / 255f,
            alpha = color.alpha
        )
    }

    fun lightenColor(color: Color, factor: Float = 0.3f): Color {
        return Color(
            red = color.red + (1 - color.red) * factor,
            green = color.green + (1 - color.green) * factor,
            blue = color.blue + (1 - color.blue) * factor,
            alpha = color.alpha
        )
    }
}
fun convertDecimeters(decimeters: Double): Pair<Double, Double> {
    val meters = decimeters / 10 // 1 decimeter = 0.1 meters
    val feet = decimeters / 3.048 // 1 decimeter = 0.328084 feet
    return Pair(meters, feet)
}

fun convertHectograms(hectograms: Double): Pair<Double, Double> {
    val kilograms = hectograms / 10 // 1 hectogram = 0.1 kilograms
    val pounds = hectograms * 0.220462 // 1 hectogram = 0.220462 pounds
    return Pair(kilograms, pounds)
}

fun roundToTwoDecimals(value: Double): Double {
    return BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toDouble()
}