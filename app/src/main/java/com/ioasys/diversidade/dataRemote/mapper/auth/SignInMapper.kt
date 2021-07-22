package com.ioasys.diversidade.dataRemote.mapper.auth

import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import retrofit2.Response

object SignInMapper {

    fun toData(res: Response<User>) = User(
        token = res.body()!!.token,
        user = UserData(
            id = res.body()!!.user.id,
            firstName = res.body()!!.user.firstName,
            lastName = res.body()!!.user.lastName,
            email = res.body()!!.user.email,
            telephone = res.body()!!.user.telephone,
            isAdmin = res.body()!!.user.isAdmin,
            isDeleted = res.body()!!.user.isDeleted,
        )
    )

}