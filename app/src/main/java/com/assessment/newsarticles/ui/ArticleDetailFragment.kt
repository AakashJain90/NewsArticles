package com.assessment.newsarticles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.assessment.newsarticles.BR
import com.assessment.newsarticles.R
import com.assessment.newsarticles.databinding.FragmentArticleDetailsBinding
import com.assessment.newsarticles.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import android.content.Intent

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailsBinding, NewsArticlesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_details

    override val viewModel: NewsArticlesViewModel
        get() = getSharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view.findViewById<Button>(R.id.btnShareArticle).setOnClickListener { shareArticle() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding().article = viewModel.articleSelected
    }

    private fun shareArticle() {
        startActivity(Intent.createChooser(viewModel.articleShareIntent(context), getString(R.string.share_via)))
    }
}