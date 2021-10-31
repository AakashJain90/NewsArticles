package com.assessment.newsarticles.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.assessment.newsarticles.data.model.Article
import com.assessment.newsarticles.databinding.RowItemArticleBinding
import com.assessment.newsarticles.ui.base.BaseRecyclerViewAdapter

class ArticlesAdapter(items: MutableList<Article>, val listener:IArticleSelectedListener) : BaseRecyclerViewAdapter<Article>(items) {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myViewHolder = MyViewHolder(
            RowItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        myViewHolder.binding.cardArticle.setOnClickListener {
            val position: Int = it.tag as Int
            listener.onArticleSelected(items[position])
        }

        return myViewHolder
    }

    inner class MyViewHolder(val binding: RowItemArticleBinding) : BaseViewHolder(binding.root) {

        override fun onBind(position: Int) {
            binding.article = items[position]
            binding.executePendingBindings()
            binding.cardArticle.tag = position
        }
    }

    interface IArticleSelectedListener {
        fun onArticleSelected(article:Article)
    }

}