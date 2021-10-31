package com.assessment.newsarticles

import com.assessment.newsarticles.data.model.Article
import org.junit.Assert
import org.junit.Test

class ArticleTest {

    @Test
    fun testObject(){
        var article: Article? = null

        Assert.assertNull(article)

        article = Article("Dog", "A dog is an animal", "https://testurl.com", "by any author", emptyList())
        Assert.assertNotNull(article)
    }

    @Test
    fun testAttributes(){

        val article = Article("Dog", "A dog is an animal", "https://testurl.com", "by any author", emptyList())

        assert(article.title == "Dog")

        assert(article.abstract == "A dog is an animal")

        assert(article.url == "https://testurl.com")

        assert(article.byline == "by any author")
        
    }
}