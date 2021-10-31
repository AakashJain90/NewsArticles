package com.assessment.newsarticles.utils

import com.assessment.newsarticles.data.model.Article

object FakeArticles {
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