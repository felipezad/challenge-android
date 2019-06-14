package com.example.lodjinha.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.lodjinha.presentation.dagger.ApplicationComponent


@Suppress("UNCHECKED_CAST")
val Activity.injector
    get() = (application as ComponentProvider<ApplicationComponent>).component

inline fun <reified T : Activity> Activity.startActivityWithoutBundle(nextActivity: Class<T>) {
    startActivity(Intent(this, nextActivity))
}

inline fun <reified T : Activity> Context.startActivityWithProductId(nextActivity: Class<T>, productId: Long) {
    val intent = Intent(this, nextActivity)
    intent.putExtra("productId", productId)
    startActivity(intent)
}
