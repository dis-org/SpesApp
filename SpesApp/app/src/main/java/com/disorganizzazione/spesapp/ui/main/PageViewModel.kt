package com.disorganizzazione.spesapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Questa classe serivrà soprattutto se un giorno permetteremo agli utenti di ruotare lo schermo,
 * ma non credo che nessuno ne senta l'esigenza.
 * This class will be especially useful if, one day, we will allow our users to rotate the screen,
 * but I doubt anyone needs this feature.
 */

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    // Non so assolutamente quale sia il proposito di questo file ma penso che questa funzione mi dirà in che fragment sto
    // I literally have no idea what this file is but I think this function will tell me which fragment I'm looking at
    fun getIndex(): Int? {
        return _index.value
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}