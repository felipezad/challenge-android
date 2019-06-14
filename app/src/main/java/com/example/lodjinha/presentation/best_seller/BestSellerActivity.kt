package com.example.lodjinha.presentation.best_seller

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.lodjinha.R
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.injector
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_best_seller.*
import kotlinx.android.synthetic.main.content_best_seller.*

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

        with(productDetail) {
            Glide.with(this@BestSellerActivity)
                .load(urlImagem)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.logo_navbar)
                .into(imageViewProductImage)
            textViewProductName.text = nome
            textViewProductPriceFrom.text = precoDe.toString()
            textViewProductPriceTo.text = precoPor.toString()
            textViewProductDescription.text = descricao.fromHtml()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_seller)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        setupViewModel()
        val productBestSeller = intent?.extras?.getLong("productId")
        productBestSeller?.let {
            bestSellerViewModel.getBestSellerProduct(it)
        }

    }

    private fun String.fromHtml(): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(this)
        }
    }
}
