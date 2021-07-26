package com.ioasys.diversidade.domain.useCases

import com.ioasys.diversidade.domain.base.UseCase
import com.ioasys.diversidade.domain.exceptions.MissingParamsException
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<User, SignUpUseCase.Params>() {

    data class Params(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val telephone: String
    )

    override suspend fun execute(params: Params?) = when(params) {
        null -> throw MissingParamsException()
        else -> try {
            params.let {
                authRepository.signUp(
                    email = params.email,
                    password = params.password,
                    firstName = params.firstName,
                    lastName = params.lastName,
                    telephone = params.telephone
                )
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}