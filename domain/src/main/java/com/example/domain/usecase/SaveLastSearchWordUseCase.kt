package com.example.domain.usecase

import com.example.domain.adapters.IUserCacheRepository
import javax.inject.Inject

class SaveLastSearchWordUseCase @Inject constructor(private val userCacheRepository: IUserCacheRepository) {
    operator fun invoke(query: String) {
        userCacheRepository.saveLastSearchedQuery(query)
    }
}