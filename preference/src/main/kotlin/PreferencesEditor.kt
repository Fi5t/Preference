package ru.freedomlogic.preference

import android.content.SharedPreferences
import android.content.Context
import android.preference.PreferenceManager
import java.util.HashSet

public fun SharedPreferences.Editor.erase() {
    clear()
    apply()
}

public fun preferencesEditor (context: Context, vararg pairs: Pair<String, Any>): SharedPreferences.Editor {
    val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()

    for ((key, value) in pairs) {
        when (value) {
            is String -> editor.putString(key, value)
            is Set<*> -> {
                if (!value.all { it is String }) {
                    throw IllegalArgumentException("Only Set<String> is supported")
                }
                [suppress("UNCHECKED_CAST")]
                editor.putStringSet(key, value as Set<String>)
            }
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported value type: ${value.javaClass}")
        }
    }

    (pairs.size() > 0) let { editor.apply() }

    return editor
}