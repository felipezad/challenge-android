package com.example.lodjinha.presentation

import android.app.Activity
import com.example.lodjinha.presentation.dagger.ApplicationComponent


@Suppress("UNCHECKED_CAST")
val Activity.injector
    get() = (application as ComponentProvider<ApplicationComponent>).component