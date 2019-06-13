package com.example.lodjinha.presentation.about

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.lodjinha.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbarAbout)
        ActionBarDrawerToggle(
            this,
            drawerAbout,
            toolbarAbout,
            R.string.open_drawer,
            R.string.close_drawer
        ).apply {
            drawerAbout.addDrawerListener(this)
            this.syncState()
        }
        with(navigationAbout) {
            itemIconTintList = null
            setNavigationItemSelectedListener(this@AboutActivity)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                finish()
            }
        }
        drawerAbout.closeDrawer(GravityCompat.START)
        return true
    }
}
