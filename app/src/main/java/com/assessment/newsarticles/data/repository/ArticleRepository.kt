package com.assessment.newsarticles.data.repository

import android.content.Context
import com.assessment.newsarticles.data.network.ArticleService
import com.assessment.newsarticles.R
import com.assessment.newsarticles.data.model.ArticlesInternalResponse
import com.assessment.newsarticles.utils.isNetworkAvailable

class ArticleRepository(
    private val context: Context,
    private val articleService: ArticleService
) : IArticleRepository {

    override suspend fun getTopStories(): ArticlesInternalResponse {
        if (!context.isNetworkAvailable()) {
            return ArticlesInternalResponse.Fail(context.getString(R.string.network_unavail))
        }

        // fetch from network
        val retroResponse = articleService.getTopStories()

        // failure checks
        if (!retroResponse.isSuccessful) {
            return ArticlesInternalResponse.Fail(retroResponse.message())
        }
        val serviceResponse = retroResponse.body()
        if (serviceResponse == null || !serviceResponse.isSuccessful()) {
            return ArticlesInternalResponse.Fail(context.getString(R.string.error_fetching_articles))
        }

        return ArticlesInternalResponse.Success(serviceResponse.results.orEmpty())
    }

}