package com.alokomkar.mapper.di

import com.alokomkar.mapper.IUserStocksMapper
import com.alokomkar.mapper.UserStocksMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsUserStocksMapper(userStocksMapper: UserStocksMapper): IUserStocksMapper

}