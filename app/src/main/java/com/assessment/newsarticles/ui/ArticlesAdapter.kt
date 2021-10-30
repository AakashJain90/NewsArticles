package com.assessment.newsarticles.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assessment.newsarticles.data.model.Article
import com.assessment.newsarticles.databinding.RowItemArticleBinding

class ArticlesAdapter(private val items: MutableList<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {

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

    data class MyViewHolder(val binding: RowItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.article = items[position]
        holder.binding.executePendingBindings()
    }
}