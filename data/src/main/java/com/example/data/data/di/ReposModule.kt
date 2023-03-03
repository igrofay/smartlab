package com.example.data.data.di

import com.example.data.data.repos.AppReposImpl
import com.example.data.data.repos.AuthenticationReposImpl
import com.example.data.data.repos.CatalogReposImpl
import com.example.data.data.repos.UserReposImpl
import com.example.data.domain.repos.AppRepos
import com.example.data.domain.repos.AuthenticationRepos
import com.example.data.domain.repos.CatalogRepos
import com.example.data.domain.repos.UserRepos
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {
    @Binds
    internal abstract fun appRepos(appRepos: AppReposImpl): AppRepos

    @Binds
    internal abstract fun userRepos(userReposImpl: UserReposImpl): UserRepos

    @Binds
    internal abstract fun authenticationRepos(authenticationReposImpl: AuthenticationReposImpl): AuthenticationRepos

    @Binds
    internal abstract fun catalogRepos(catalogReposImpl: CatalogReposImpl) : CatalogRepos
}