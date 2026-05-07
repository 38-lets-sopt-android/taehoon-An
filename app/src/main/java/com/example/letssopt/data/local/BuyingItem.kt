package com.example.letssopt.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buying_items")
data class BuyingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "buying_item_image")
    val image:Int,

    @ColumnInfo(name = "buying_item_title")
    val title: String
)
