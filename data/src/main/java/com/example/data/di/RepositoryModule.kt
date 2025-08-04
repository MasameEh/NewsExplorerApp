package com.example.data.di

import com.example.data.datasource.remote.INewsRemoteDataSource
import com.example.data.datasource.remote.NewsRemoteDataSourceImp
import com.example.domain.adapters.INewsRepository
import com.example.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepository: NewsRepositoryImpl): INewsRepository

    @Binds
    @Singleton
    abstract fun bindNewsRemoteDataSource(newsRemoteDataSource: NewsRemoteDataSourceImp): INewsRemoteDataSource
}