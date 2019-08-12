package com.agagrzebyk.mye_shop

import android.os.Bundle
import android.util.Log.d
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.agagrzebyk.mye_shop.model.Product

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.actionHome -> d("daniel", "Going home")
                R.id.actionJewelry -> d("daniel", "fancy earrings with dimonds")
                R.id.actionEngagement -> d("daniel", "Will You merry me")
                R.id.actionWatches -> d("daniel", "What time is it?")
                R.id.actionAccessories -> d("daniel", "Happy Birthday!")
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

        val products = arrayListOf<Product>()

        for(i in 0..100){
            products.add(Product("Berry Ring #$i", "https://via.placeholder.com/150/0000FF", 1.99))
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductsAdapter(products)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
     //   return true
        return super.onOptionsItemSelected(item)
    }


}
