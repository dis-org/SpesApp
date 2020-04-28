package com.disorganizzazione.spesapp.utils

import android.widget.EditText

/**
 * Miscellaneous extension functions used here and there.
 */

/**
 * Replaces empty strings with null, leaves nonempty strings unchanged.
 * Only used in EditText.getValue(), but it was worth it.
 */
fun String.nullIfEmpty() = if (this == "") null else this

/**
 * Gets the String? contained in an EditText.
 */
fun EditText.getContent() = this.text.toString().nullIfEmpty()