package com.example.nero.weatherappkotlin

import com.example.nero.weatherappkotlin.domain.commands.DayForecastCommand
import com.example.nero.weatherappkotlin.domain.datasources.ForecastProvider
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestDayCommandTest {

    @Test
    fun testProviderIsCalled() {
        val provider = mock(ForecastProvider::class.java)
        val command = DayForecastCommand(2, provider)

        command.execute()
        verify(provider).requestForecast(2)
    }

}