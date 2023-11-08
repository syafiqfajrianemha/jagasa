package com.emha.jagasa.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emha.jagasa.data.JagasaRepository
import com.emha.jagasa.ui.screen.detail.DetailViewModel
import com.emha.jagasa.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: JagasaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}