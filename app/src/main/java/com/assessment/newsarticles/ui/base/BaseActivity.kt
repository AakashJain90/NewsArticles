package com.assessment.newsarticles.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val bindingVariable: Int

    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doDataBinding()
    }

    private fun doDataBinding() {
        DataBindingUtil.setContentView<T>(this, layoutId).apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
        }

    }

}