package com.itunes.searchapi.response

import java.io.Serializable

data class Result(
    val artistId: Int?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val collectionExplicitness: String?,
    val collectionPrice: Double?,
    val collectionName : String?,
    val country: String?,
    val currency: String?,
    val kind: String?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackCensoredName: String?,
    val trackExplicitness: String?,
    val trackId: Int?,
    val trackName: String?,
    val trackPrice: Double?,
    val trackTimeMillis: Int?,
    val trackViewUrl: String?,
    val wrapperType: String?
):Serializable