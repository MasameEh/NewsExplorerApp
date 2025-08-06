package com.example.domain.usecase

import com.example.domain.interfaces.IUserCacheRepository
import javax.inject.Inject

class GetLastSearchWordUseCase @Inject constructor(private val userCacheRepository: IUserCacheRepository) {
    operator fun invoke(): String? {
        return userCacheRepository.getLastSearchedQuery()
    }
}