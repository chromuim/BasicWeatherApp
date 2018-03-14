package com.example.nero.weatherappkotlin.data.db

import com.example.nero.weatherappkotlin.data.db.extensions.byId
import com.example.nero.weatherappkotlin.data.db.extensions.clear
import com.example.nero.weatherappkotlin.data.db.extensions.parseList
import com.example.nero.weatherappkotlin.data.db.extensions.parseOpt
import com.example.nero.weatherappkotlin.domain.data.Forecast
import com.example.nero.weatherappkotlin.domain.data.ForecastList
import com.example.nero.weatherappkotlin.domain.datasources.ForecastDataSource
import com.example.nero.weatherappkotlin.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id)
                .parseOpt { DayForecast(HashMap(it)) }

        forecast?.let { dataMapper.convertDayToDomain(it) }
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID}= ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        city?.let { dataMapper.convertToDomain(it) }

    }


    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}