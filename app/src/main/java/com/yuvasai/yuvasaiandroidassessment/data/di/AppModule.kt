package com.yuvasai.yuvasaiandroidassessment.data.di

import com.yuvasai.yuvasaiandroidassessment.common.Constants
import com.yuvasai.yuvasaiandroidassessment.data.remote.FetchListItemsApi
import com.yuvasai.yuvasaiandroidassessment.data.repository.ListItemsRepositoryImpl
import com.yuvasai.yuvasaiandroidassessment.domain.repository.ListItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFetchListItemsApi(): FetchListItemsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchListItemsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesListItemsRepositoryImpl(api: FetchListItemsApi): ListItemsRepository {
        return ListItemsRepositoryImpl(api)
    }
}