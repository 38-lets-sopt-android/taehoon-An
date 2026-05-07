package com.example.letssopt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BuyingItem::class], version = 1)
abstract class BuyingItemDatabase : RoomDatabase(){
    abstract fun buyingItemDao() : BuyingItemDao

    companion object {
        @Volatile
        private var INSTANCE: BuyingItemDatabase? = null

        fun getDatabase(context: Context): BuyingItemDatabase{
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    BuyingItemDatabase::class.java,
                    "buying_items_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
