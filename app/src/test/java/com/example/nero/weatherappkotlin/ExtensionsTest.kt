package com.example.nero.weatherappkotlin

import com.example.nero.weatherappkotlin.extensions.toDateString
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {
    @Test
    fun testLongToDateString() {
        assertEquals("Mar 12, 2018", 1520809200000L.toDateString())
    }

}