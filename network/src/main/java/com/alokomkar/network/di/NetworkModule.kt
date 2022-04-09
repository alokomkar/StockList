package com.alokomkar.network.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.alokomkar.network.BuildConfig
import com.alokomkar.network.api.UserStocksAPI
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @VisibleForTesting
    private val BASE_URL = BuildConfig.BASE_URL
    private val TIMEOUT_SECOND: Long = 15

    @Singleton
    @Provides
    fun provideCurrencyApi(retrofit: Retrofit): UserStocksAPI
            = retrofit.create(UserStocksAPI::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit
            = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient
            = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
        .addInterceptor { interceptorChain ->
            val request = interceptorChain.request().newBuilder().build()
            interceptorChain.proceed(request)
        }
        .apply {
            if(BuildConfig.DEBUG) {
                addInterceptor(chuckerInterceptor)
            }
        }
        .build()

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        // Create the Collector
        val chuckerCollector = ChuckerCollector(
            context = context,
            // Toggles visibility of the push notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        // Create the Interceptor
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            .build()
    }

}