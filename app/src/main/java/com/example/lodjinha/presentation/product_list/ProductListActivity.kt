package com.example.lodjinha.presentation.product_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var productListAdapter: ProductListAdapter

    private fun setupViewModel() {
        productViewModel.productListUseCase.observe(this, Observer { productList ->
            if (productList.isNotEmpty()) {
                if (!::productListAdapter.isInitialized) {
                    createProductList(productList)
                } else {
                    productListAdapter.addProducts(productList)
                }
            }
            loadingProgressBar.hide()
        })
    }

    private fun createProductList(productList: List<Product>) {
        productListAdapter = ProductListAdapter(productList, Glide.with(this))
        recyclerViewProductList.adapter = productListAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        retrieveProductList()
        initView()
    }

    private fun retrieveProductList() {
        intent?.extras?.getLong("categoryId")?.let {
            productViewModel.getListProductByCategory(it)
            productViewModel.categoryId = it
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

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (!loadingProgressBar.isVisible &&
                        linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2
                    ) {
                        loadingProgressBar.show()
                        productViewModel.currentOffset += 20
                        productViewModel.getListProductByCategory(
                            productViewModel.categoryId,
                            productViewModel.currentOffset
                        )
                    }
                }
            })
        }

    }
}
