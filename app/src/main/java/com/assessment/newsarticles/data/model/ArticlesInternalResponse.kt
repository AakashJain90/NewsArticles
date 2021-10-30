package com.assessment.newsarticles.data.model

sealed class ArticlesInternalResponse {

    data class Success(val listArticles: List<Article>) : ArticlesInternalResponse()

    data class Fail(val errorMsg: String) : ArticlesInternalResponse()

}
