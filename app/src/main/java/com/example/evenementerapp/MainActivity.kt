package com.example.evenementerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Setting toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // getting Navigation Drawer
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout) //Se puede hacer con el binding
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.MainHostFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf( //Los fragments en los que aparecera el navigation drawer, sino aparece la flechita
                R.id.homeFragment, R.id.aboutFragment, R.id.qrScannerFragment
            ), drawerLayout
        )
        //Hace que el navigation drawer sea visible en la barra
        setupActionBarWithNavController(navController, appBarConfiguration)
        //Hace que el menu del navigation drawer funcione
        navView.setupWithNavController(navController)
        //Para esconder o aparecer cosas de la barra segun el fragment en el que esta

    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        //val searchItem = menu?.findItem(R.id.search_icon_bar)
        //val searchView = searchItem?.actionView as SearchView
        val saveItem = menu?.findItem(R.id.save_bar)
        val aboutItem = menu?.findItem(R.id.nav_about)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.nav_add_question) {
                saveItem.setVisible(true)
            } else {
                saveItem.setVisible(false)
            }
            if(destination.id == R.id.nav_home) {
                aboutItem.setVisible(true)
            } else {
                aboutItem.setVisible(false)
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return onNavDestinationSelected(item!!,
            findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.MainHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}

