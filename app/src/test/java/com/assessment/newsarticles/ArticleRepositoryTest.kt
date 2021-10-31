package com.assessment.newsarticles

import android.content.Context
import com.assessment.newsarticles.data.model.ArticlesInternalResponse
import com.assessment.newsarticles.data.model.ArticlesServiceResponse
import com.assessment.newsarticles.data.network.ArticleService
import com.assessment.newsarticles.data.repository.ArticleRepository
import com.assessment.newsarticles.utils.FakeArticles
import com.assessment.newsarticles.utils.isNetworkAvailable
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class ArticleRepositoryTest {

    private lateinit var repository: ArticleRepository

    private val networkUnavailable = "Network Unavailable"
    private val networkError = "Network Error Occurred"

    private val context = mockk<Context> {
        coEvery { getString(R.string.network_unavail) } returns networkUnavailable
    }
    private val service = mockk<ArticleService>()

    @Before
    fun setup() {
        repository = ArticleRepository(context, service)
    }

    @Test
    fun test_get_top_stories_with_network_unavailable_should_return_fail_response() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns false

        val topStories = repository.getTopStories()

        assert(topStories is ArticlesInternalResponse.Fail)
    }

    @Test
    fun test_get_top_stories_with_network_unavailable_should_return_error_msg() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns false

        val topStories = repository.getTopStories()

        assert((topStories as ArticlesInternalResponse.Fail).errorMsg == networkUnavailable)
    }

    @Test
    fun test_get_top_stories_with_network_error_should_return_fail_response() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns true
        val resp: Response<ArticlesServiceResponse> = mockk {
            coEvery { isSuccessful } returns false
            coEvery { message() } returns networkError
        }
        coEvery { service.getTopStories() } returns resp

        val topStories = repository.getTopStories()

        assert(topStories is ArticlesInternalResponse.Fail)
        assert((topStories as ArticlesInternalResponse.Fail).errorMsg == networkError)
    }

    @Test
    fun test_get_top_stories_with_valid_network_should_return_Success() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns true
        val articleServiceResp: ArticlesServiceResponse = mockk {
            coEvery { isSuccessful() } returns true
            coEvery { results } returns listOf()
        }
        val resp: Response<ArticlesServiceResponse> = mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns articleServiceResp
        }
        coEvery { service.getTopStories() } returns resp

        val topStories = repository.getTopStories()

        assert(topStories is ArticlesInternalResponse.Success)
    }

    @Test
    fun test_get_top_stories_with_valid_network_should_return_correct_articles_response() = runBlocking {
        coEvery { context.isNetworkAvailable() } returns true
        val articleServiceResp: ArticlesServiceResponse = mockk {
            coEvery { isSuccessful() } returns true
            coEvery { results } returns FakeArticles.articleList
        }
        val resp: Response<ArticlesServiceResponse> = mockk {
            coEvery { isSuccessful } returns true
            coEvery { body() } returns articleServiceResp
        }
        coEvery { service.getTopStories() } returns resp

        val topStories = repository.getTopStories()

        val listArticlesResp = (topStories as ArticlesInternalResponse.Success).listArticles
        assert(listArticlesResp.size == FakeArticles.articleList.size)

        // each element contains in original data set.
        for (article in listArticlesResp) {
            assert(FakeArticles.articleList.contains(article))
        }
    }
}