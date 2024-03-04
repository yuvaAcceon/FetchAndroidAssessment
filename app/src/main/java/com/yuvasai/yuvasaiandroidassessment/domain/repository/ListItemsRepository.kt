package com.yuvasai.yuvasaiandroidassessment.domain.repository

import com.yuvasai.yuvasaiandroidassessment.data.remote.dto.FetchListItemDto

interface ListItemsRepository {
    suspend fun getListItems(): List<FetchListItemDto>
}