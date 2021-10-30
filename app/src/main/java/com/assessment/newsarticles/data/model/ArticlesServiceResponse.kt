package com.assessment.newsarticles.data.model

class ArticlesServiceResponse {

    var status: String? = null

    var results: List<Article>? = null

    fun isSuccessful() : Boolean = (status?.equals("ok", true) == true)

}