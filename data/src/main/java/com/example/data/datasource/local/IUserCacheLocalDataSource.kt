package com.example.data.datasource.local

interface IUserCacheLocalDataSource {

    fun saveLastSearchedQuery(query: String)
    fun getLastSearchedQuery(): String?
}