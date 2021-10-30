package com.assessment.newsarticles.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assessment.newsarticles.data.model.Article
import com.assessment.newsarticles.data.model.ArticlesInternalResponse
import com.assessment.newsarticles.data.repository.IArticleRepository
import com.assessment.newsarticles.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsArticlesViewModel(private val repository: IArticleRepository) : BaseViewModel() {

    private var topStoriesArticlesResponse : ArticlesInternalResponse? = null

    val articlesList = MutableLiveData<List<Article>>()


    init {
        viewModelScope.launch {
            isLoading.value = true

            topStoriesArticlesResponse = repository.getTopStories()

            isLoading.value = false

            when(topStoriesArticlesResponse) {
                is ArticlesInternalResponse.Fail -> toastMsg.value = (topStoriesArticlesResponse as ArticlesInternalResponse.Fail).errorMsg
                is ArticlesInternalResponse.Success -> articlesList.value = (topStoriesArticlesResponse as ArticlesInternalResponse.Success).listArticles
            }


        }
    }


}