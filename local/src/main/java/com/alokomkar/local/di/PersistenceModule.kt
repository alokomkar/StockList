package com.alokomkar.local.di

import android.content.Context
import androidx.room.Room
import com.alokomkar.local.StocksDatabase
import com.alokomkar.local.dao.IUserStocksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    private val appDatabase = "stocks_list.db"

    @Singleton
    @Provides
    fun providesUserStocksDao(database: StocksDatabase): IUserStocksDao = database.userStocksDao()

    @Singleton
    @Provides
    internal fun provideDatabase(@ApplicationContext context: Context): StocksDatabase {
        return Room.databaseBuilder(context, StocksDatabase::class.java, appDatabase)
            .fallbackToDestructiveMigration()
            .build()
    }

}