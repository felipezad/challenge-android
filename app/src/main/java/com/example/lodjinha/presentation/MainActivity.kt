package com.example.lodjinha.presentation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.lodjinha.R
import com.example.lodjinha.presentation.dagger.ApplicationComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProviders
            .of(this, injector.mainViewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getProductList(categoriaId = 4)
    }

    override fun onPause() {
        mainViewModel.destroy()
        super.onPause()
    }
}

val Activity.injector get() = (application as ComponentProvider<ApplicationComponent>).component
