package com.example.mutualmobile.widget

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mutualmobile.R
import com.example.mutualmobile.data.models.Article
import com.example.mutualmobile.presentation.NewsUiEvent
import com.example.mutualmobile.presentation.viewmodel.NewsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    newsType: String,
    viewModel: NewsViewModel = getViewModel()
) {
    LaunchedEffect(key1 = newsType) {
        viewModel.postUiEvent(NewsUiEvent.FetchNewsArticle(newsType))
    }
    val content by viewModel.uiState.collectAsState()
    when {
        content.isLoading -> {
            Loader()
        }
        content.showErrorDialog -> {
            ErrorWidget(
                onPositiveClick = {
                    viewModel.postUiEvent(NewsUiEvent.FetchNewsArticle(newsType))
                },
                onNegativeClick = {

                }
            )
        }
        else -> NewsListItems(modifier, content.data)
    }

}

@Composable
fun NewsListItems(
    modifier: Modifier,
    items: List<Article>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.dimen_10dp))
    ) {
        items(items) {
            NewsArticleCard(
                modifier = modifier, article = it
            )
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsArticleCard(
    modifier: Modifier,
    article: Article
) {
    val context = LocalContext.current
    val intent = article.link?.let {
        remember {
            Intent(Intent.ACTION_VIEW, Uri.parse(it))
        }
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(id = R.dimen.dimen_10dp),
                start = dimensionResource(id = R.dimen.dimen_10dp),
                end = dimensionResource(id = R.dimen.dimen_10dp)
            ),
        elevation = dimensionResource(id = R.dimen.dimen_5dp),
        onClick = {
            intent?.let {
                context.startActivity(intent)
            } ?: run { Toast.makeText(context, "No link Present to open", Toast.LENGTH_SHORT).show() }
        }
    ) {
        Column {
            article.imageUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "Article Image",
                    modifier = modifier
                        .height(dimensionResource(id = R.dimen.dimen_194dp)),
                    contentScale = ContentScale.FillBounds
                )
            }

            Text(
                text = article.description ?: article.title ?: "No description",
                fontSize = 14.sp,
                modifier = modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.dimen_16dp),
                        bottom = dimensionResource(id = R.dimen.dimen_16dp),
                        start = dimensionResource(id = R.dimen.dimen_16dp),
                        end = dimensionResource(id = R.dimen.dimen_16dp)
                    )
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun NewsArticleCardPreview() {
    NewsArticleCard(
        modifier = Modifier,
        article = Article(
            title = "Megerősítve a Ghostwire",
            link = "",
            description = "A tegnap esti órákban lezajlott PlayStation Showcase online " +
                    "esemény részeként minden, és valóban minden a Ghostwire: Tokyo című" +
                    " videojátékról szólt,"
        )
    )
}