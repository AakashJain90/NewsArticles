package com.assessment.newsarticles.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    val items: MutableList<T>,
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }

    abstract class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        abstract fun onBind(position: Int)
    }

}