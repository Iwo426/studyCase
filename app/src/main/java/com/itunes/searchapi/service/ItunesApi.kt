package com.itunes.searchapi.service

import com.itunes.searchapi.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItunesApi {

    @GET("search")
    suspend fun getSearchListt(
        @Query("term") term: String?,
        @Query("entity") entity: String?,
        @Query("limit") limit: Int?,
        @Query("offset") page: Int?
    ): SearchResponse
}