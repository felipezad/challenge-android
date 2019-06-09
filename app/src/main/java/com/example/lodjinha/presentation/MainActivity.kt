package com.example.lodjinha.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lodjinha.R
import com.example.lodjinha.data.dagger.DaggerLodjinhaServiceComponent
import com.example.lodjinha.data.dagger.LodjinhaServiceComponent
import com.example.lodjinha.data.remote.LodjinhaService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ComponentProvider<LodjinhaServiceComponent> {

    override val component: LodjinhaServiceComponent by lazy {
        DaggerLodjinhaServiceComponent
            .factory()
            .create()
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

}
