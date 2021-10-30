package com.assessment.newsarticles.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.assessment.newsarticles.data.model.Article
import com.assessment.newsarticles.databinding.RowItemArticleBinding
import com.assessment.newsarticles.ui.base.BaseRecyclerViewAdapter

class ArticlesAdapter(items: MutableList<Article>) : BaseRecyclerViewAdapter<Article>(items) {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class MyViewHolder(val binding: RowItemArticleBinding) : BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            binding.article = items[position]
            binding.executePendingBindings()
        }
    }

}