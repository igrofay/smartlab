package com.example.data.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class RegularHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthorizedHttpClient