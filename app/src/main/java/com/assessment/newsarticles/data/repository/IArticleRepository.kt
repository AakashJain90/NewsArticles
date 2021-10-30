package com.assessment.newsarticles.data.repository

import com.assessment.newsarticles.data.model.ArticlesInternalResponse

interface IArticleRepository {

    suspend fun getTopStories() : ArticlesInternalResponse

}