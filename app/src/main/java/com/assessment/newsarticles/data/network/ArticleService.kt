package com.assessment.newsarticles.data.network

import com.assessment.newsarticles.BuildConfig
import com.assessment.newsarticles.data.model.ArticlesServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleService {

    @GET("/svc/topstories/v2/world.json?api-key=${BuildConfig.API_KEY}")
    suspend fun getTopStories(): Response<ArticlesServiceResponse>

}