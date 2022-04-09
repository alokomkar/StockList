package com.alokomkar.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alokomkar.local.model.UserStocks
import kotlinx.coroutines.flow.Flow

@Dao
interface IUserStocksDao {

    @Query("SELECT * FROM user_stocks")
    fun getAllAsFlow(): Flow<List<UserStocks>>

    @Query("SELECT * FROM user_stocks")
    fun getAll(): List<UserStocks>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userStocks: List<UserStocks>)

    @Query("DELETE FROM user_stocks")
    fun deleteAll()

}