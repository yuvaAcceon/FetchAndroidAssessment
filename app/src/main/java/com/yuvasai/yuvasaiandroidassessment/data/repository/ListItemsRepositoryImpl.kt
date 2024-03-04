package com.yuvasai.yuvasaiandroidassessment.data.repository

import com.yuvasai.yuvasaiandroidassessment.data.remote.FetchListItemsApi
import com.yuvasai.yuvasaiandroidassessment.data.remote.dto.FetchListItemDto
import com.yuvasai.yuvasaiandroidassessment.domain.repository.ListItemsRepository
import javax.inject.Inject

class ListItemsRepositoryImpl @Inject constructor(
    private val api: FetchListItemsApi
) : ListItemsRepository {
    override suspend fun getListItems(): List<FetchListItemDto> {
        return api.getItems()
    }
}