package com.example.lodjinha.presentation.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.lodjinha.R
import com.example.lodjinha.domain.category.Category
import com.example.lodjinha.presentation.product_list.ProductListActivity
import kotlinx.android.synthetic.main.category_page.view.*


class CategoryAdapter(private val categorys: List<Category>, private val requestManager: RequestManager) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_page, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categorys[position], requestManager)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ProductListActivity::class.java)
            intent.putExtra("categoryId", categorys[position].id)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = categorys.size

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imageViewCategory: ImageView = view.imageViewCategory
        private var textViewProductCategory: TextView = view.textViewProductCategory

        fun bind(category: Category, requestManager: RequestManager) {

            requestManager
                .load(category.urlImagem.replace("http:", "https:"))
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.logo_navbar)
                .into(imageViewCategory)

            textViewProductCategory.text = category.descricao

            // Intent to Category List

        }
    }
}