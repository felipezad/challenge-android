package com.example.lodjinha.presentation.best_seller

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.lodjinha.R

import kotlinx.android.synthetic.main.activity_best_seller.*

class BestSellerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_seller)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val int = intent.extras.getLong("productId")
        Log.d("productId", int.toString())
    }

}
