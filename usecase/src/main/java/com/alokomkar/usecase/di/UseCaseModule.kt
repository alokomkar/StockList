package com.alokomkar.usecase.di

import com.alokomkar.repository.di.RepositoryModule
import com.alokomkar.usecase.FetchUserPortfolioUseCase
import com.alokomkar.usecase.IFetchUserPortfolioUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindFetchUserPortfolioUseCase(fetchUserPortfolioUseCase: FetchUserPortfolioUseCase): IFetchUserPortfolioUseCase

}