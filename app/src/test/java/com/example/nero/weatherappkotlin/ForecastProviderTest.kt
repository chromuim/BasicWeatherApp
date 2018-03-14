package com.example.nero.weatherappkotlin

import com.example.nero.weatherappkotlin.domain.data.Forecast
import com.example.nero.weatherappkotlin.domain.data.ForecastList
import com.example.nero.weatherappkotlin.domain.datasources.ForecastDataSource
import com.example.nero.weatherappkotlin.domain.datasources.ForecastProvider
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.*

class ForecastProviderTest {

    @Test
    fun dataSourceReturnValues() {
        val ds = mock(ForecastDataSource::class.java)
        `when`(ds.requestDayForecast(0)).then { Forecast(0, 0, "desc", 0, 0, "") }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))
    }

    @Test
    fun emptyDataSourceReturnValueFromServer() {
        val db = mock(ForecastDataSource::class.java)
        val server = mock(ForecastDataSource::class.java)
        `when`(server.requestForecastByZipCode(any(Long::class.java), any(Long::class.java)))
                .then { ForecastList(0, "city", "country", listOf()) }


        val provider = ForecastProvider(listOf(db, server))
        assertNotNull(provider.requestByZipcode(0, 0))

    }
}