package com.assessment.newsarticles.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.newsarticles.BR
import com.assessment.newsarticles.R
import com.assessment.newsarticles.databinding.FragmentArticleListBinding
import com.assessment.newsarticles.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class ArticleListFragment : BaseFragment<FragmentArticleListBinding, NewsArticlesViewModel>(),
    SearchView.OnQueryTextListener {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_list

    override val viewModel: NewsArticlesViewModel
        get() = getSharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setHasOptionsMenu(true)

        // recycle view setup.
        viewDataBinding().rvArticles.layoutManager = LinearLayoutManager(this.context)
        viewDataBinding().rvArticles.adapter = ArticlesAdapter(mutableListOf())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false;
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.searchArticles(newText, context)
        return false
    }

}