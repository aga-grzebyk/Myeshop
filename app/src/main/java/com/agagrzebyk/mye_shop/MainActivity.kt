package com.agagrzebyk.mye_shop

import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.agagrzebyk.mye_shop.Database.AppDatabase
import com.agagrzebyk.mye_shop.Database.ProductFromDatabase
import com.agagrzebyk.mye_shop.model.Product

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        doAsync {

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null, "Necklace - Gold Beads", 75.99))
            val products = db.productDao().getAll()

            uiThread {
                d("aga", "products size? ${products.size}")
            }
        }


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, MainFragment())
            .commit()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MainFragment())
                        .commit()
                }
                R.id.actionJewelry -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, JewelryFragment())
                        .commit()

                }
                R.id.actionEngagement -> d("aaa", "Will You merry me")
                R.id.actionWatches -> d("aaa", "What time is it?")
                R.id.actionAccessories -> d("aaa", "Happy Birthday!")
                R.id.actionAndmin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, AdminFragment())
                        .commit()
                }
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.actionCard){
            return true
        }
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}
