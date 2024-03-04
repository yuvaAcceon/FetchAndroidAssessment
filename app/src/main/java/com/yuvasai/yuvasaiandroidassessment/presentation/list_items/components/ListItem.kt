package com.yuvasai.yuvasaiandroidassessment.presentation.list_items.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yuvasai.yuvasaiandroidassessment.domain.model.FetchListItem

@Composable
fun ListItem(
    listItem: FetchListItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${listItem.listId}",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1F)
        )
        Text(
            text = "${listItem.name}",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1F)
        )
        Text(
            text = "${listItem.id}",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1F)
        )
    }
}