package com.example.lodjinha.presentation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.lodjinha.R
import com.example.lodjinha.domain.banner.Banner
import com.example.lodjinha.domain.category.Category
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.dagger.ApplicationComponent
import kotlinx.android.synthetic.main.activity_drawer_layout.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProviders
            .of(this, injector.mainViewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun setupViewModel() {
        mainViewModel.banner.observe(this, Observer { bannerList ->
            updateBannerAdapter(bannerList)
        })

        mainViewModel.productCategories.observe(this, Observer { categories ->
            updateCategoriesAdapter(categories)
        })
        mainViewModel.bestSellersList.observe(this, Observer { bestSellersList ->
            updateBestSellerAdapter(bestSellersList)
        })
    }

    private fun initLayout() {

        setContentView(R.layout.activity_drawer_layout)

        setSupportActionBar(toolbarLodjinha)

        ActionBarDrawerToggle(
            this,
            drawerLodjinha,
            toolbarLodjinha,
            R.string.open_drawer,
            R.string.close_drawer
        ).apply {
            drawerLodjinha.addDrawerListener(this)
            this.syncState()
        }

        recyclerViewBanner.apply {
            setHasFixedSize(true)
        }
        recyclerViewCategories.apply {
            setHasFixedSize(true)
        }
    }

    private fun updateBannerAdapter(it: List<Banner>) {
        recyclerViewBanner.adapter = BannerAdapter(it, Glide.with(this))
    }

    private fun updateCategoriesAdapter(it: List<Category>) {
        recyclerViewCategories.adapter = CategoryAdapter(it, Glide.with(this))
    }

    private fun updateBestSellerAdapter(it: List<Product>) {
        recyclerViewBestSellers.adapter = BestSellerAdapter(it, Glide.with(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getBanner()
        mainViewModel.getProductCategory()
        mainViewModel.getBestSellersList()
    }

    override fun onPause() {
        mainViewModel.destroy()
        super.onPause()
    }
}

val Activity.injector get() = (application as ComponentProvider<ApplicationComponent>).component
