package com.assessment.newsarticles

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assessment.newsarticles.repositories.FakeArticlesRepository
import com.assessment.newsarticles.ui.NewsArticlesViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsArticlesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_viewmodel_with_network_error_response() {
        val repository = FakeArticlesRepository(true)

        val viewModel = NewsArticlesViewModel(repository)

        val msgResponse = viewModel.toastMsg.getOrAwaitValueTest()

        assert(msgResponse.equals(repository.networkError))
    }

    @Test
    fun test_viewmodel_with_valid_network_response() {
        val repository = FakeArticlesRepository()

        val viewModel = NewsArticlesViewModel(repository)

        val articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        assert(articleListResp.size == repository.articleList.size)

        // each element contains in original data set inside the repository.
        for (article in articleListResp) {
            assert(repository.articleList.contains(article))
        }
    }

    @Test
    fun test_search_articles_with_a_long_false_filters_to_verify_empty_search_results() {
        val context = mockk<Context>()
        val repository = FakeArticlesRepository()
        val viewModel = NewsArticlesViewModel(repository)
        var articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        viewModel.searchArticles("xyzabc123qwertyetc.etc.", context)
        articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        assert(articleListResp.isEmpty())
    }

    @Test
    fun test_search_articles_with_a_valid_filter_to_verify_correct_search_results() {
        val context = mockk<Context>()
        val repository = FakeArticlesRepository()
        val viewModel = NewsArticlesViewModel(repository)
        var articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        viewModel.searchArticles("Dog", context)
        articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        assert(articleListResp.isNotEmpty())
        assert(articleListResp.get(0) == repository.fakeArticleDog)
    }

    @Test
    fun test_search_articles_with_empty_filter_to_verify_all_search_results() {
        val context = mockk<Context>()
        val repository = FakeArticlesRepository()
        val viewModel = NewsArticlesViewModel(repository)
        var articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        viewModel.searchArticles("", context)
        articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        assert(articleListResp.size == repository.articleList.size)

        // each element contains in original data set inside the repository.
        for (article in articleListResp) {
            assert(repository.articleList.contains(article))
        }
    }

    @Test
    fun test_search_articles_with_null_filter_to_verify_all_search_results() {
        val context = mockk<Context>()
        val repository = FakeArticlesRepository()
        val viewModel = NewsArticlesViewModel(repository)
        var articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        viewModel.searchArticles(null, context)
        articleListResp = viewModel.articlesList.getOrAwaitValueTest()

        assert(articleListResp.size == repository.articleList.size)

        // each element contains in original data set inside the repository.
        for (article in articleListResp) {
            assert(repository.articleList.contains(article))
        }
    }
}