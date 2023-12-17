package com.coding.meet.medistockapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.coding.meet.medistockapp.fragments.Beranda
import com.coding.meet.medistockapp.fragments.DataObat
import com.coding.meet.medistockapp.fragments.ObatKeluar
import com.coding.meet.medistockapp.fragments.ObatMasuk
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }

    }

    private fun onBackPressedMethod() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            finish()
        }
    }

    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)

        val navigationView = findViewById<NavigationView>(R.id.navView)
        val header = navigationView.getHeaderView(0)
        val userNameTxt = findViewById<TextView>(R.id.userNameTxt)
        val emailTxt = findViewById<TextView>(R.id.emailTxt)
        val profileImg = findViewById<ImageView>(R.id.profileImg)

        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        // Default Fragment Load and menu select
        replaceFragment(Beranda())
        navigationView.setCheckedItem(R.id.nav_home)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home -> {
                replaceFragment(Beranda())
                title = "Beranda"
            }
            R.id.nav_obat-> {
                replaceFragment(DataObat())
                title = "Data Obat"
            }
            R.id.nav_masuk -> {
                replaceFragment(ObatMasuk())
                title = "Obat Masuk"
            }
            R.id.nav_keluar -> {
                replaceFragment(ObatKeluar())
                title = "Obat Keluar"
            }
            R.id.nav_logout -> {
                Toast.makeText(this,"Logout",Toast.LENGTH_LONG).show()

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .commit()
    }
}