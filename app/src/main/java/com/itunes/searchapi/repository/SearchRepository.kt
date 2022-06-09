package com.itunes.searchapi.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itunes.searchapi.response.SearchResponse
import com.itunes.searchapi.service.ItunesApi
import com.itunes.searchapi.response.Result

class SearchRepository(
    private val api: ItunesApi,
    var page: Int,
    private val term: String,
    private val entity: String
) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: SearchResponse = api.getSearchListt(term,entity,20,page)
            page += 20
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.results.size) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}