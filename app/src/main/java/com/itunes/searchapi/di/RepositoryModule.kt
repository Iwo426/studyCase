package com.itunes.searchapi.di

import com.itunes.searchapi.repository.SearchRepository
import com.itunes.searchapi.service.ItunesApi
import com.itunes.searchapi.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Singleton
    @Provides
    fun provideSearchRepository(
        itunesApi: ItunesApi,
    ): SearchViewModel {
        return SearchViewModel(itunesApi)
    }

}














