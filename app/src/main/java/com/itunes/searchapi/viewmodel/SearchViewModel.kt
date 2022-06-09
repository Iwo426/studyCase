package com.itunes.searchapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itunes.searchapi.repository.SearchRepository
import com.itunes.searchapi.response.Result
import com.itunes.searchapi.service.ItunesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val api: ItunesApi
) : ViewModel() {

    fun searchMovie(
        page: Int,
        term : String,
        entity: String,
    ): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                SearchRepository(api, page,term, entity)
            }).flow
    }
}



















