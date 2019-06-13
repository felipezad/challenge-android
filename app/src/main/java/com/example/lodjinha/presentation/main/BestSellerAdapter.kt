package com.example.lodjinha.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.lodjinha.R
import com.example.lodjinha.domain.product.Product
import kotlinx.android.synthetic.main.best_seller_page.view.*


class BestSellerAdapter(private val products: List<Product>, private val requestManager: RequestManager) :
    RecyclerView.Adapter<BestSellerAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.best_seller_page, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], requestManager)

    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imageViewProduct: ImageView = view.imageViewProduct
        private var textViewProductDescription: TextView = view.textViewProductDescription
        private var textViewPriceFrom: TextView = view.textViewPrecoDe
        private var textViewPriceTo: TextView = view.textViewPrecoPor

        fun bind(product: Product, requestManager: RequestManager) {

            requestManager
                .load(product.urlImagem.replace("http:", "https:"))
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.logo_navbar)
                .into(imageViewProduct)

            textViewProductDescription.text = product.nome
            textViewPriceFrom.text = product.precoDe.toString()
            textViewPriceTo.text = product.precoPor.toString()

            // Intent to Product Detail

        }
    }
}