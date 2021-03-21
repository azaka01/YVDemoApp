package com.intsoftdev.data.di

import com.intsoftdev.data.network.UserApiService
import com.intsoftdev.data.repository.UserRepositoryImpl
import com.intsoftdev.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://randomuser.me/"
private const val DEFAULT_TIMEOUT = 30L

@InstallIn(SingletonComponent::class)
@Module
internal object DataModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(okHttpClient: OkHttpClient): UserApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
            .build()

}

@Module
@InstallIn(ActivityComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepository: UserRepositoryImpl
    ): UserRepository
}