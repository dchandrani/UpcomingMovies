package com.app.upcomingmovies.di

import com.app.upcomingmovies.BuildConfig
import com.app.upcomingmovies.data.Repository
import com.app.upcomingmovies.network.ApiInterface
import com.app.upcomingmovies.ui.detail.MovieDetailViewModel
import com.app.upcomingmovies.ui.list.MovieListViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { Repository(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build().create(ApiInterface::class.java)
    }

    single {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(ApiInterface.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApiInterface.REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApiInterface.REQUEST_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            okHttpClient.addInterceptor(interceptor)
        }

        val authInterceptor = Interceptor { chain ->
            val request = chain.request()
            val httpURL = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.ApiKey).build()
            chain.proceed(request.newBuilder().url(httpURL).build())
        }

        okHttpClient.addInterceptor(authInterceptor).build()
    }
}

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }

    viewModel { MovieDetailViewModel(getProperty("id"), get()) }
}