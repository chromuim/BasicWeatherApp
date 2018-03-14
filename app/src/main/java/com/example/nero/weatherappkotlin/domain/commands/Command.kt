package com.example.nero.weatherappkotlin.domain.commands

interface Command<out T> {
    fun execute(): T
}