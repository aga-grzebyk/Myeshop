package com.agagrzebyk.mye_shop

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        product_name.text = title

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title is in stock")
                .setPositiveButton("OK") { p0, p1 -> }
                .create()
                .show()
        }

    }
}