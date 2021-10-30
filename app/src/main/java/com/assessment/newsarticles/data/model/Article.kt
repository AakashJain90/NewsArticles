package com.assessment.newsarticles.data.model

data class Article(val title: String, val abstract: String, val url: String, val byline: String, val multimedia: List<ArticleMedia>)
