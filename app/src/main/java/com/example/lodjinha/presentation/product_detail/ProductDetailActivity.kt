package com.example.lodjinha.presentation.product_detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.lodjinha.R
import com.example.lodjinha.domain.product.BookProductUseCase
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.injector
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.content_best_seller.*

class ProductDetailActivity : AppCompatActivity() {

    private val productDetailViewModel by lazy {
        ViewModelProviders
            .of(this, injector.productDetailViewModel)
            .get(ProductDetailViewModel::class.java)
    }

    private fun setupViewModel() {
        productDetailViewModel.productDetail.observe(this, Observer { productDetail ->
            updateBestSellerDetail(productDetail)
        })
        productDetailViewModel.productBooked.observe(this, Observer { productBooked ->
            displayResult(productBooked)
        })
    }

    private fun displayResult(result: BookProductUseCase.Result) {
        Snackbar.make(productDetailContainer, getString(R.string.buy_more), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.return_activity)) { finish() }.show()
    }

    private fun updateBestSellerDetail(productDetail: Product) {
        with(productDetail) {
            Glide.with(this@ProductDetailActivity)
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

    private fun initView() {
        setContentView(R.layout.activity_product_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val productBestSeller = intent?.extras?.getLong("productId")
        productBestSeller?.let {
            productDetailViewModel.getBestSellerProduct(it)
        }
        fabBookProduct.setOnClickListener { _ ->
            productDetailViewModel.productDetail.value?.id?.let { productDetailViewModel.bookProduct(it) }
            fabBookProduct.hide()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        initView()
    }

    override fun onDestroy() {
        productDetailViewModel.destroy()
        super.onDestroy()
    }

    private fun String.fromHtml(): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(this)
        }
    }
}
