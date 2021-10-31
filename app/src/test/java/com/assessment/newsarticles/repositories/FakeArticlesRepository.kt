package com.assessment.newsarticles.repositories

import com.assessment.newsarticles.data.model.Article
import com.assessment.newsarticles.data.model.ArticlesInternalResponse
import com.assessment.newsarticles.data.repository.IArticleRepository

class FakeArticlesRepository(private val shouldReturnNetworkError: Boolean = false) :
    IArticleRepository {

    val networkError = "Network Error"

    override suspend fun getTopStories(): ArticlesInternalResponse {
        if (shouldReturnNetworkError) {
            return ArticlesInternalResponse.Fail(networkError)
        }

        return ArticlesInternalResponse.Success(articleList)
    }

    val fakeArticleDog = Article(
        "Dog has 4 legs.", "Dog is a best creature", "https://sampleurl.com",
        "by AnyAuthor", emptyList()
    )
    val fakeArticleCat = Article(
        "Cat has 4 legs.", "Cat is a best creature", "https://sampleurl.com",
        "by AnyAuthor", emptyList()
    )
    val articleList = listOf(fakeArticleDog, fakeArticleCat)
}