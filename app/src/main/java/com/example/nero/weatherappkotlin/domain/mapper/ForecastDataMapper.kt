package com.example.nero.weatherappkotlin.domain.mapper

import com.example.nero.weatherappkotlin.data.Forecast
import com.example.nero.weatherappkotlin.data.ForecastResult
import com.example.nero.weatherappkotlin.domain.data.ForecastList
import com.example.nero.weatherappkotlin.domain.data.Forecast as DomainForecastModel
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ForecastDataMapper {

    fun convertFromDataToDomain(zipCode: Long, forecastResult: ForecastResult) = with(forecastResult) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(forecastResult.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<DomainForecastModel> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastToDomain(forecast.copy(dt = dt))
        }
    }


    private fun convertForecastToDomain(forecast: Forecast) = with(forecast) {
        DomainForecastModel(-1, dt, forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),
                generateIcon(forecast.weather[0].icon))
    }

    private fun generateIcon(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

}