package com.agagrzebyk.mye_shop

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.agagrzebyk.mye_shop.Database.AppDatabase
import com.agagrzebyk.mye_shop.Database.ProductFromDatabase
import com.agagrzebyk.mye_shop.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_main.view.categoriesRecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.net.URL

class MainFragment  : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val categories = listOf("RINGS", "BRACELETS", "NECKLACE", "PENSADNS", "EARRINGS", "BROOCHES", "CHARMS", "WATCHES", "ACCESSORIES")

        root.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener{
            // 1 - we do stuff on the background Thread
            doAsync {

                // 2 - we set up the Room database. We get the references to it
                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()

                // 3 - we get the product from database
                val productsFromDatabase = db.productDao().searchFor("%${searchTerm.text}%")

                // 4 - we convert it from Database/ProductFromDatabase to model/Product
                val products = productsFromDatabase.map {
                    Product(
                        it.title,
                        "https://agagrzebyk.pl/data/face_02.jpg",
                        it.price,
                        true
                    )
                }
                uiThread {
                    recycler_view.apply {
                        layoutManager = GridLayoutManager(activity,2)
                        adapter = ProductsAdapter(products)
                    }
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}