package com.assessment.newsarticles.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // configure action bar.
        val navController = findNavController(R.id.fragment_navigation_host)
        setupActionBarWithNavController(navController, AppBarConfiguration(navController.graph))
    }

}