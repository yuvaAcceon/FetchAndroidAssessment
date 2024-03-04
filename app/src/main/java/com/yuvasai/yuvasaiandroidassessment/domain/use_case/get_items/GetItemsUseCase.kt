package com.yuvasai.yuvasaiandroidassessment.domain.use_case.get_items

import android.content.res.Resources
import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import com.yuvasai.yuvasaiandroidassessment.R
import com.yuvasai.yuvasaiandroidassessment.common.Resource
import com.yuvasai.yuvasaiandroidassessment.data.remote.dto.FetchListItemDto
import com.yuvasai.yuvasaiandroidassessment.data.remote.dto.toFetchListItem
import com.yuvasai.yuvasaiandroidassessment.domain.model.FetchListItem
import com.yuvasai.yuvasaiandroidassessment.domain.repository.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ListItemsRepository
) {
    operator fun invoke(): Flow<Resource<List<FetchListItem>>> = flow {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                Build.VERSION_CODES.S
            ) >= 7
        ) {
            try {
                emit(Resource.Loading())
                val listItems = mutableListOf<FetchListItem>()

                val groupedItems = mutableMapOf<Int, MutableList<FetchListItemDto>>()

                for (item in repository.getListItems()) {
                    if (!item.name.isNullOrBlank()) {
                        groupedItems.getOrPut(item.listId) { mutableListOf() }.add(item)
                    }
                }

                groupedItems.forEach { (_, items) ->
                    items.sortBy { it.name?.substring(5, it.name.length)?.toInt() }
                }

                groupedItems.toSortedMap().forEach { (listId, items) ->
                    println("List $listId:")
                    items.forEach { item ->
                        listItems.add(item.toFetchListItem())
                    }
                }

                emit(Resource.Success(listItems))

            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: Resources.getSystem()
                            .getString(R.string.http_exception)
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: Resources.getSystem().getString(R.string.io_exception)
                    )
                )
            }
        } else {
            try {
                emit(Resource.Loading())
                val listItems = mutableListOf<FetchListItem>()

                val groupedItems = mutableMapOf<Int, MutableList<FetchListItemDto>>()

                for (item in repository.getListItems()) {
                    if (!item.name.isNullOrBlank()) {
                        groupedItems.getOrPut(item.listId) { mutableListOf() }.add(item)
                    }
                }

                groupedItems.forEach { (_, items) ->
                    items.sortBy { it.name?.substring(5, it.name.length)?.toInt() }
                }

                groupedItems.toSortedMap().forEach { (listId, items) ->
                    println("List $listId:")
                    items.forEach { item ->
                        listItems.add(item.toFetchListItem())
                    }
                }

                emit(Resource.Success(listItems))

            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: Resources.getSystem().getString(R.string.io_exception)
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: Resources.getSystem()
                            .getString(R.string.http_exception)
                    )
                )
            }
        }
    }
}