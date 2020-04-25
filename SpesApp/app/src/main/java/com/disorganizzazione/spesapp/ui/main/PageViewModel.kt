package com.disorganizzazione.spesapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Auto-generated class which may become more useful if we allow screen rotation (unlikely).
 */

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()

    // This is actually useful because it tells you which fragment you're in
    fun getIndex(): Int? {
        return _index.value
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}