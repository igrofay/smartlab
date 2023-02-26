package com.example.data.data.di

import com.example.data.data.repos.AppReposImpl
import com.example.data.domain.repos.AppRepos
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReposModule {
    @Binds
    internal abstract fun appRepos(appRepos: AppReposImpl): AppRepos
}