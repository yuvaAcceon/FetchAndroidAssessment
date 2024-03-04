package com.yuvasai.yuvasaiandroidassessment.data.remote.dto

import com.yuvasai.yuvasaiandroidassessment.domain.model.FetchListItem

data class FetchListItemDto(
    val id: Int,
    val listId: Int,
    val name: String?
)

fun FetchListItemDto.toFetchListItem(): FetchListItem {
    return FetchListItem(
        id = id,
        listId = listId,
        name = name
    )
}