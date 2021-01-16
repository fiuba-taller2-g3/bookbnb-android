package com.fiuba.bookbnb.domain.user

import java.io.Serializable

data class UserData(val name: String, val surname: String, val email: String, val password: String?, val gender: String,
                    val phoneNumber: String, val birthDate: String, val id: String, val walletId: String) : Serializable