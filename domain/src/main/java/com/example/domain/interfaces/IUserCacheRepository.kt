package com.example.domain.interfaces

interface IUserCacheRepository {
    fun saveLastSearchedQuery(query: String)
    fun getLastSearchedQuery(): String?
}