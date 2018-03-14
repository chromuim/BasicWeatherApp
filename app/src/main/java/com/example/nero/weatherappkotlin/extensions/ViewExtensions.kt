package com.example.nero.weatherappkotlin.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}