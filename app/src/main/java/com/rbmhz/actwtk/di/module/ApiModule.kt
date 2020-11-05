package com.rbmhz.actwtk.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rbmhz.actwtk.BuildConfig
import com.rbmhz.actwtk.api.imgur.InterfaceAPI

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class ApiModule {

    //region Retrofit
    private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    //Initializing logging interceptor
    init {
        if (BuildConfig.DEBUG)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun providesOkHttpClient(@Named("HttpHeaders") headers: Map<String, String> = mapOf()): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        if (headers.isNotEmpty()) {
            clientBuilder.addInterceptor { chain ->
                val req = chain.request().newBuilder()
                headers.map { header ->
                    req.addHeader(header.key, header.value)
                }
                chain.proceed(req.build())
            }
        }
        return clientBuilder.build()
    }

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.IMGUR_URL)
            .client(client)
            .build()
    }
    //endregion


    //region ProvidesApi
    @Provides
    fun providesImgurGalleryApi(retrofit: Retrofit): InterfaceAPI {
        return retrofit.create(InterfaceAPI::class.java)
    }
    //endregion


    @Provides
    @Named("HttpHeaders")
    fun providesDefaultHttpHeaders(): Map<String, String> {
        return hashMapOf(
            "Authorization" to "Client-ID ${BuildConfig.IMGUR_CLIENT_ID}"
        )
    }
}