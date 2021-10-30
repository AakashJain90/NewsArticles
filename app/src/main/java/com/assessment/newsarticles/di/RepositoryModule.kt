package com.assessment.newsarticles.di

import com.assessment.newsarticles.data.repository.ArticleRepository
import com.assessment.newsarticles.data.repository.IArticleRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val repositoryModule = module {

    single { ArticleRepository(androidContext(), get<Retrofit>().create()) } binds arrayOf(IArticleRepository::class)

}