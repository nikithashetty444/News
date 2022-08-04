package com.example.mutualmobile.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mutualmobile.R
import com.example.mutualmobile.presentation.NewsUiEvent
import com.example.mutualmobile.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchHostScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = getViewModel()
) {
    val content by viewModel.uiState.collectAsState()
    Column {
        SearchBar(modifier = modifier, viewModel = viewModel)
        Spacer(
            modifier = modifier.size(
                dimensionResource(id = R.dimen.dimen_16dp),
                dimensionResource(id = R.dimen.dimen_16dp)
            )
        )
        when {
            content.isLoading -> {
                CircularProgressIndicator()
            }
            content.showErrorDialog -> {
                TODO("Handle error")
            }
            else -> NewsListItems(modifier = modifier, items = content.data)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier,
    hint: String = "Search",
    viewModel: NewsViewModel
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintVisible by remember {
        mutableStateOf(hint != "")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_16dp))
    ) {
        var job: Job? = null
        BasicTextField(
            value = text,
            onValueChange = {
                job?.cancel()
                text = it
                job = MainScope().launch {
                    delay(500)
                    viewModel.postUiEvent(NewsUiEvent.SearchLatestNews(it))
                }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(Color.Black),
            modifier = modifier
                .fillMaxWidth()
                .shadow(dimensionResource(id = R.dimen.dimen_5dp), CircleShape)
                .background(Color.White, CircleShape)
                .padding(10.dp)
                .onFocusChanged {
                    isHintVisible = !it.isFocused
                }
        )
        if (isHintVisible) {
            Text(
                text = hint,
                color = Color.Gray,
                modifier = modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.dimen_16dp),
                        vertical = dimensionResource(id = R.dimen.dimen_12dp)
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchBar() {
    // SearchBar(modifier = Modifier)
}