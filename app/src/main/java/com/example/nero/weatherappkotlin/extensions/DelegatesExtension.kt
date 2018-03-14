package com.example.nero.weatherappkotlin.extensions

import android.content.Context
import kotlin.reflect.KProperty

class Preference<T>(val context: Context, val name: String, val default: T) {


    object DelegateExt {
        fun <T> preference(context: Context, name: String, default: T) =
                Preference(context, name, default)
    }

    val prefs by lazy { context.getSharedPreferences(name, Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        return putPreference(name, value)
    }


    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("this type can't be found")
        }
        res as T
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        val res = when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw  IllegalArgumentException("this type can't be saved")
        }
        res.apply()

    }
}
