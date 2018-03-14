package com.example.nero.weatherappkotlin.data.server

import com.example.nero.weatherappkotlin.api.Request
import com.example.nero.weatherappkotlin.data.db.ForecastDb
import com.example.nero.weatherappkotlin.domain.data.Forecast
import com.example.nero.weatherappkotlin.domain.data.ForecastList
import com.example.nero.weatherappkotlin.domain.datasources.ForecastDataSource
import com.example.nero.weatherappkotlin.domain.mapper.ForecastDataMapper


class ForecastServer(private val dataMapper: ForecastDataMapper = ForecastDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast? {
        throw UnsupportedOperationException()
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = Request(zipCode).run()
        val converted = dataMapper.convertFromDataToDomain(zipCode, result)
        forecastDb.saveForecast(converted);
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}