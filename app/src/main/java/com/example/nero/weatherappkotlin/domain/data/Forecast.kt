package com.example.nero.weatherappkotlin.domain.data

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)