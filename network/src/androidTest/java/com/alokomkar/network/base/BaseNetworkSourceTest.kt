package com.alokomkar.network.base

import com.alokomkar.network.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.reflect.KClass

@ExperimentalCoroutinesApi
abstract class BaseNetworkSourceTest<S : Any>(service: KClass<S>) {

    lateinit var mockWebServer: MockWebServer
    private lateinit var okhttp: OkHttpClient

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private val baseUrl: String by lazy { BuildConfig.BASE_URL }

    private val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    val serviceLive: S by lazy {
        Retrofit.Builder()
            .client(okhttp)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(service)
    }

    val serviceMock: S by lazy {
        Retrofit.Builder()
            .client(okhttp)
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(service)
    }

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        okhttp = OkHttpClient.Builder()
            .addInterceptor { interceptorChain ->
                val request = interceptorChain.request().newBuilder().build()
                interceptorChain.proceed(request)
            }
            .followSslRedirects(true)
            .build()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    fun enqueueResponse(filePath: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferSource = inputStream?.source()?.buffer() ?: return
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse.setBody(
                bufferSource.readString(Charsets.UTF_8)
            )
        )
        println(
            "🍏 enqueueResponse() ${Thread.currentThread().name}," +
                    " ${bufferSource.readString(Charsets.UTF_8).length} $mockResponse"
        )
    }

    private fun <T : Any> Retrofit.create(service: KClass<T>): T = create(service.javaObjectType)

}