package com.example.lodjinha.presentation.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.lodjinha.R
import com.example.lodjinha.domain.banner.Banner
import kotlinx.android.synthetic.main.banner.view.*


class BannerAdapter(private val banners: List<Banner>, private val requestManager: RequestManager) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(banners[position], requestManager)

    }

    override fun getItemCount(): Int = banners.size

    class BannerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var imageViewBanner: ImageView = view.imageViewBanner

        fun bind(banner: Banner, requestManager: RequestManager) {

            requestManager
                .load(banner.linkUrl)
                .placeholder(R.drawable.logo_menu)
                .into(imageViewBanner)

            imageViewBanner.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(banner.linkUrl)
                view.context.startActivity(i)
            }

        }
    }
}