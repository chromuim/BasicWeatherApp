package com.example.nero.weatherappkotlin.domain.data

data class ForecastList(val id: Long, val city: String, val country: String,  val dailyForecast: List<Forecast>) {
    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]

}