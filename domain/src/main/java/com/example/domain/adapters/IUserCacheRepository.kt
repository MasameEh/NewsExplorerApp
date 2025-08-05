package com.example.domain.adapters

interface IUserCacheRepository {
    fun saveLastSearchedQuery(query: String)
    fun getLastSearchedQuery(): String?
}