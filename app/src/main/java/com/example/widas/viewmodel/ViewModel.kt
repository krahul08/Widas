package com.example.widas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.widas.model.Repository

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository()

    fun getAllData(): MutableLiveData<out Any>? {
        return repository.mutableLiveData
    }
}