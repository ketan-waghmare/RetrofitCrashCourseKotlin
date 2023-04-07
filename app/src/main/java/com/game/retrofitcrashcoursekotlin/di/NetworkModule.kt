package com.game.retrofitcrashcoursekotlin.di

import com.game.retrofitcrashcoursekotlin.api.AuthInterceptor
import com.game.retrofitcrashcoursekotlin.api.LocationAPI
import com.game.retrofitcrashcoursekotlin.api.UserAPI
import com.game.retrofitcrashcoursekotlin.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder) : UserAPI {
        return retrofitBuilder.build().create(UserAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesLocationAPI(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient) : LocationAPI {
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(LocationAPI::class.java)
    }
}