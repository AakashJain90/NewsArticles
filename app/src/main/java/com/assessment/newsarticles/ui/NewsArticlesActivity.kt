package com.assessment.newsarticles.ui

import com.assessment.newsarticles.BR
import com.assessment.newsarticles.R
import com.assessment.newsarticles.databinding.ActivityMainBinding
import com.assessment.newsarticles.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class NewsArticlesActivity : BaseActivity<ActivityMainBinding, NewsArticlesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: NewsArticlesViewModel
        get() = getViewModel()

}