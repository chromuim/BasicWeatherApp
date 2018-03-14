package com.example.nero.weatherappkotlin.domain.datasources

import com.example.nero.weatherappkotlin.data.db.ForecastDb
import com.example.nero.weatherappkotlin.data.server.ForecastServer
import com.example.nero.weatherappkotlin.domain.data.Forecast
import com.example.nero.weatherappkotlin.domain.data.ForecastList
import com.example.nero.weatherappkotlin.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = SOURCES) {

    companion object {
        val DAY_IN_MILL_SEC = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }


    fun requestByZipcode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())

        if (res != null && res.size >= days) res else null
    }


    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

//    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
//        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
//        return if (res != null && res.size >= days) res else null
//    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILL_SEC * DAY_IN_MILL_SEC

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}