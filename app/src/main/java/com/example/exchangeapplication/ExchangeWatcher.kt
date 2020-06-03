package com.example.exchangeapplication

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlin.math.max
import kotlin.math.min

class ExchangeWatcher(watcherEditText: EditText) : TextWatcher {
    private var isBlocked = false
    val editText: EditText = watcherEditText
    var oppositeWatcher: ExchangeWatcher? = null
    var conversionRatio = 1.0

    fun block() {
        isBlocked = true
    }

    fun unblock() {
        isBlocked = false
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
        if (isBlocked) {
            return
        }
        val str = p0.toString()
        val num: Double? = str.toDoubleOrNull()
        if (num != null) {
            oppositeWatcher?.block()

            val oppositeRatio: Double = oppositeWatcher?.conversionRatio ?: 1.0
            val converted = num * conversionRatio / oppositeRatio

            val power: Int = max(0, Math.floor(Math.log10(converted)).toInt())
            val bound: Int = min(converted.toString().length, power + 6) - 1
            val rounded = converted.toString().substring(0..bound)

            val out = Editable.Factory.getInstance().newEditable(rounded)
            oppositeWatcher?.editText?.text = out

            oppositeWatcher?.unblock()
        }
    }
}