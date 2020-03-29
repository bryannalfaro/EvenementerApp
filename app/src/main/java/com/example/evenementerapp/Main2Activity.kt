package com.example.evenementerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private lateinit var drawerLayoutInflater: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        drawerLayoutInflater=findViewById(R.id.drawerLayout)


        val navController= this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(
            this,navController,drawerLayoutInflater
        )

        NavigationUI.setupWithNavController(navView,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayoutInflater)
    }
}
