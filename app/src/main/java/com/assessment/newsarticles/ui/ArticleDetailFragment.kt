package com.assessment.newsarticles.ui

import android.os.Bundle
import android.view.View
import com.assessment.newsarticles.BR
import com.assessment.newsarticles.R
import com.assessment.newsarticles.databinding.FragmentArticleDetailsBinding
import com.assessment.newsarticles.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailsBinding, NewsArticlesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_details

    override val viewModel: NewsArticlesViewModel
        get() = getSharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding().article = viewModel.articleSelected
    }

}