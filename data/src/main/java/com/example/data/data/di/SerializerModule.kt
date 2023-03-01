package com.example.data.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.ExperimentalMultiProcessDataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.example.data.data.data_source.serializer.UserBodySerializer
import com.example.data.data.model.UserBody
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SerializerModule {

    @Provides
    @Singleton
    @OptIn(ExperimentalMultiProcessDataStore::class)
    internal fun provideDataStore(
        @ApplicationContext context: Context,
        userBodySerializer: UserBodySerializer,
    ): DataStore<UserBody?> =
        MultiProcessDataStoreFactory.create(
            serializer = userBodySerializer,
            produceFile = {
                File("${context.cacheDir.path}/${userBodySerializer.fileName}")
            }
        )
}