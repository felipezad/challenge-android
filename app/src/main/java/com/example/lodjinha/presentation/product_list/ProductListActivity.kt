package com.example.lodjinha.presentation.product_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.lodjinha.R
import com.example.lodjinha.domain.product.Product
import com.example.lodjinha.presentation.injector
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.content_product_list.*

class ProductListActivity : AppCompatActivity() {

    private val productViewModel by lazy {
        ViewModelProviders
            .of(this, injector.productListViewModel)
            .get(ProductListViewModel::class.java)
    }

    private fun setupViewModel() {
        productViewModel.productListUseCase.observe(this, Observer { productList ->
            updateProductList(productList)
        })
    }

    private fun updateProductList(productList: List<Product>) {
        recyclerViewProductList.adapter = ProductListAdapter(productList, Glide.with(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        retriveProductList()
        initView()
    }

    private fun retriveProductList() {
        intent?.extras?.getLong("categoryId")?.let {
            productViewModel.getListProductByCategory(it)
        }
    }

    private fun initView() {
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerViewProductList.apply {
            setHasFixedSize(true)
            this.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}
