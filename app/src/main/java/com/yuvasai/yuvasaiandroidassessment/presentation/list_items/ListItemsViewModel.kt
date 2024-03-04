package com.yuvasai.yuvasaiandroidassessment.presentation.list_items

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuvasai.yuvasaiandroidassessment.R
import com.yuvasai.yuvasaiandroidassessment.common.Resource
import com.yuvasai.yuvasaiandroidassessment.domain.use_case.get_items.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListItemsViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ListItemsState())
    val state: State<ListItemsState> = _state

    init {
        getListItems()
    }

    private fun getListItems() {
        getItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ListItemsState(listItems = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = ListItemsState(
                        error = result.message ?: Resources.getSystem().getString(
                            R.string.unexpected_error
                        )
                    )
                }

                is Resource.Loading -> {
                    _state.value = ListItemsState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}