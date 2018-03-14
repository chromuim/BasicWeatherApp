package com.example.nero.weatherappkotlin.domain.commands

import com.example.nero.weatherappkotlin.domain.data.ForecastList
import com.example.nero.weatherappkotlin.domain.datasources.ForecastProvider

class ForecastCommand(private val zipCode: Long,
                      private val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipcode(zipCode, DAYS)

}