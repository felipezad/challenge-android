package com.example.lodjinha.presentation.best_seller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lodjinha.R
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.injector

class BestSellerActivity : AppCompatActivity() {

    private val bestSellerViewModel by lazy {
        ViewModelProviders
            .of(this, injector.bestSellersViewModel)
            .get(BestSellerViewModel::class.java)
    }

    private fun setupViewModel() {
        bestSellerViewModel.bestSellerDetail.observe(this, Observer { productDetail ->
            updateBestSellerDetail(productDetail)
        })
    }

    private fun updateBestSellerDetail(productDetail: Product) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_seller)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val productBestSeller = intent?.extras?.getLong("productId")
        productBestSeller?.let {
            bestSellerViewModel.getBestSellerProduct(it)
        }
    }

}
