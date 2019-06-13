package com.example.lodjinha.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.lodjinha.R
import com.example.lodjinha.domain.banner.Banner
import com.example.lodjinha.domain.category.Category
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.about.AboutActivity
import com.example.lodjinha.presentation.dagger.ApplicationComponent
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drawer_layout.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
        with(navigationLodjinha) {
            itemIconTintList = null
            setNavigationItemSelectedListener(this@MainActivity)
        }

        recyclerViewBanner.apply {
            setHasFixedSize(true)
        }

        recyclerViewCategories.apply {
            setHasFixedSize(true)
        }

        recyclerViewBestSellers.apply {
            setHasFixedSize(true)
            this.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_about -> {
                startActivityWithoutBundle(AboutActivity::class.java)
            }
        }
        drawerLodjinha.closeDrawer(GravityCompat.START)
        return true
    }
}

val Activity.injector get() = (application as ComponentProvider<ApplicationComponent>).component

inline fun <reified T : Activity> Activity.startActivityWithoutBundle(nextActivity: Class<T>) {
    startActivity(Intent(this, nextActivity))
}
