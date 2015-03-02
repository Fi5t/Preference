package ru.freedomlogic.preference

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences

private val defaultInit: Any.() -> Unit = {}

public fun SharedPreferences.getString(key: String, defValue: String = ""): String = getString(key, defValue)
public fun SharedPreferences.getStringSet(key: String, defValues: Set<String> = emptySet()): Set<String> = getStringSet(key, defValues)
public fun SharedPreferences.getInt(key: String, defValue: Int = 0): Int = getInt(key, defValue)
public fun SharedPreferences.getLong(key: String, defValue: Long = 0): Long = getLong(key, defValue)
public fun SharedPreferences.getFloat(key: String, defValue: Float = 0.0f): Float = getFloat(key, defValue)
public fun SharedPreferences.getBoolean(key: String, defValue: Boolean = false): Boolean = getBoolean(key, defValue)

public fun Context.preferences(init: SharedPreferences.() -> Unit = defaultInit): SharedPreferences {
    val defaultPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    defaultPreferences.init()

    return defaultPreferences
}

public fun SharedPreferences.onChanged(listener: (SharedPreferences?, key:String?) -> Unit): Unit
        = registerOnSharedPreferenceChangeListener(listener)
