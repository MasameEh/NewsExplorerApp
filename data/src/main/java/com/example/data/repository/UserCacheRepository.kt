package com.example.data.repository

import com.example.data.datasource.local.IUserCacheLocalDataSource
import com.example.domain.adapters.IUserCacheRepository
import javax.inject.Inject

class UserCacheRepository @Inject constructor(
    private val userCacheDataSource: IUserCacheLocalDataSource
) : IUserCacheRepository {

    override fun saveLastSearchedQuery(query: String) {
        userCacheDataSource.saveLastSearchedQuery(query)
    }

    override fun getLastSearchedQuery(): String? {
        return userCacheDataSource.getLastSearchedQuery()
    }

}