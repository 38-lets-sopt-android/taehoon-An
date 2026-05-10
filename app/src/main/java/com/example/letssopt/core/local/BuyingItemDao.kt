package com.example.letssopt.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BuyingItemDao {
    @Insert
    suspend fun insert(bucket : BuyingItem)

    @Query("SELECT * FROM buying_items")
    fun getAllBuyingItems(): Flow<List<BuyingItem>>
}
