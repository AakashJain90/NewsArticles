package com.assessment.newsarticles.di

import com.assessment.newsarticles.ui.NewsArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsArticlesViewModel()
    }
}