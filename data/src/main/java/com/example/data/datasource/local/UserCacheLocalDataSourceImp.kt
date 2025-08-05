package com.example.data.datasource.local

import com.example.core.utils.Constants.LAST_SEARCHED_QUERY_KEY
import javax.inject.Inject

class UserCacheLocalDataSourceImp @Inject constructor(private val sharedPref: CacheHelper): IUserCacheLocalDataSource {

    override fun saveLastSearchedQuery(query: String) {
        sharedPref.saveString(LAST_SEARCHED_QUERY_KEY, query)
    }

    override fun getLastSearchedQuery(): String? {
        return sharedPref.getString(LAST_SEARCHED_QUERY_KEY)
    }

}