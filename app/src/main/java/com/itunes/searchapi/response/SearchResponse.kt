package com.itunes.searchapi.response

data class SearchResponse(
    val resultCount: Int,
    val results: List<Result>
)