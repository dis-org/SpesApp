package com.disorganizzazione.spesapp.utils

import android.widget.EditText

/**
 * Rimpiazza le stringhe vuote con null, lascia le stringhe non vuote inalterate.
 * Replaces empty strings with null, leaves nonempty strings unchanged.
 */
fun String.nullIfEmpty() = if (this == "") null else this

/**
 * Restituisce la String? contenuta in una EditText
 * Gets the String? contained in an EditText
 */
fun EditText.getValue() = this.text.toString().nullIfEmpty()