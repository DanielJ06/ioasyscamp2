package com.ioasys.diversidade.data.remote.dataSource

import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthDataSource {

    suspend fun signIn(email: String, password: String): Flow<User>

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ): Response<User>

    suspend fun getAccountDetails(userId: String): Response<UserData>

}