package com.example.filmsapplication.model.dataSource

import com.example.filmsapplication.model.repository.FilmsList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy

class RetrofitImpl: InetDataSource<FilmsList>{

    private val proxyHost = "216.172.52.118"
    private val proxyPort = 4555


    private val proxy: Proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(proxyHost, proxyPort))

    override suspend fun getDataAsync(
        apiKey: String,
        language: String
    ): FilmsList {
        return getService(BaseInterceptor.interceptor).getListOfFilmsAsync(apiKey, language).await()
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }
    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_LIST_OF_FILMS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createHTPPClient(interceptor))
            .build()
    }


    private fun createHTPPClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.proxy(proxy)
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    companion object {
        private const val BASE_LIST_OF_FILMS_URL = "https://api.themoviedb.org/3/"
    }
}