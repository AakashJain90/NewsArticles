package com.assessment.newsarticles.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assessment.newsarticles.R
import com.assessment.newsarticles.data.model.Article
import com.assessment.newsarticles.data.model.ArticlesInternalResponse
import com.assessment.newsarticles.data.repository.IArticleRepository
import com.assessment.newsarticles.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsArticlesViewModel(private val repository: IArticleRepository) : BaseViewModel() {

    private var topStoriesArticlesResponse: ArticlesInternalResponse? = null

    val articlesList = MutableLiveData<List<Article>>()

    init {
        viewModelScope.launch {
            isLoading.value = true

            val topStories = repository.getTopStories()
            topStoriesArticlesResponse = topStories

            isLoading.value = false

            when (topStories) {
                is ArticlesInternalResponse.Fail -> toastMsg.value = topStories.errorMsg
                is ArticlesInternalResponse.Success -> articlesList.value = topStories.listArticles
            }
        }
    }

    fun searchArticles(filterText: String?, context: Context?) {
        if (isLoading.value == true) {
            toastMsg.value = context?.getString(R.string.wait_fetching_top_stories)
            return
        }

        if (topStoriesArticlesResponse!! is ArticlesInternalResponse.Fail) {
            toastMsg.value = context?.getString(R.string.no_results)
        } else {
            val listArticles =
                (topStoriesArticlesResponse!! as ArticlesInternalResponse.Success).listArticles
            articlesList.value = if (filterText.isNullOrEmpty()) {
                listArticles
            } else {
                listArticles.filter { it.title.contains(filterText, true) }
            }
        }

    }
}