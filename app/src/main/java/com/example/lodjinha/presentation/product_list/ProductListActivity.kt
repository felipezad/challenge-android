package com.example.lodjinha.presentation.product_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lodjinha.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
