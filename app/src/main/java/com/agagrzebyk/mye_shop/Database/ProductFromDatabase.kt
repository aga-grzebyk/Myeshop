package com.agagrzebyk.mye_shop.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductFromDatabase (

    @PrimaryKey(autoGenerate = true)
    val uid : Int?,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val price: Double
)