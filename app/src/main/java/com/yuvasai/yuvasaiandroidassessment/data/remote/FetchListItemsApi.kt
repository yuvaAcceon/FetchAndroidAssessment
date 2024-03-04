package com.yuvasai.yuvasaiandroidassessment.data.remote

import com.yuvasai.yuvasaiandroidassessment.data.remote.dto.FetchListItemDto
import retrofit2.http.GET

interface FetchListItemsApi {

    @GET("/hiring.json")
    suspend fun getItems(): List<FetchListItemDto>
}