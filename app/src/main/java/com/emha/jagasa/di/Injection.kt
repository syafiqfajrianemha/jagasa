package com.emha.jagasa.di

import com.emha.jagasa.data.JagasaRepository

object Injection {
    fun provideRepository(): JagasaRepository {
        return JagasaRepository.getInstance()
    }
}