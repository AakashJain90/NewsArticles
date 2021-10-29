package com.assessment.newsarticles.ui.base

import androidx.lifecycle.ViewModel
import com.assessment.newsarticles.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val toastMsg: SingleLiveEvent<String> = SingleLiveEvent()
    val isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()

}