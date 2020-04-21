package com.armandl.nasimesahary.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityModel : ViewModel() {

    val preferenceUpdateHandler = MutableLiveData<Void>()

    internal fun preferenceIsUpdated() = preferenceUpdateHandler.postValue(null)
}
