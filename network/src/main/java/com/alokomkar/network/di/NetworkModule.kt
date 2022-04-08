package com.alokomkar.network.di

import androidx.annotation.VisibleForTesting
import com.alokomkar.network.BuildConfig
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @VisibleForTesting
    private val BASE_URL = BuildConfig.BASE_URL
    private val TIMEOUT_SECOND: Long = 15

}