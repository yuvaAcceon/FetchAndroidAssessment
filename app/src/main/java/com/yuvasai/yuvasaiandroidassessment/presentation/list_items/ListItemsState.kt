package com.yuvasai.yuvasaiandroidassessment.presentation.list_items

import com.yuvasai.yuvasaiandroidassessment.domain.model.FetchListItem

data class ListItemsState(
    val isLoading: Boolean = false,
    val listItems: List<FetchListItem> = emptyList(),
    val error: String = ""
)