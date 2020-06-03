package com.example.exchangeapplication

import android.view.View
import android.widget.AdapterView

class ExchageSpinnerListner(watcher: ExchangeWatcher,
                            coursesMap: Map<String, Double> = mapOf<String, Double>())
    : AdapterView.OnItemSelectedListener {
    val watcher = watcher
    var coursesMap = coursesMap

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val code = adapterView?.selectedItem as String
        watcher.conversionRatio = coursesMap[code]?: watcher.conversionRatio
    }
}