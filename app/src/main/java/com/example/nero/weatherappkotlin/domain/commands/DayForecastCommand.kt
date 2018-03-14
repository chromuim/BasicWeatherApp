package com.example.nero.weatherappkotlin.domain.commands

import com.example.nero.weatherappkotlin.domain.data.Forecast
import com.example.nero.weatherappkotlin.domain.datasources.ForecastProvider

class DayForecastCommand(val id: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast> {


    override fun execute(): Forecast = forecastProvider.requestForecast(id)
}