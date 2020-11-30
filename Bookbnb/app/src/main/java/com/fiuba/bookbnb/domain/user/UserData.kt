package com.fiuba.bookbnb.domain.user

import java.io.Serializable

data class UserData(val name: String, val surname: String, val email: String, val password: String?, val type: String,
                    val gender: String, val phoneNumber: String, val birthDate: String, val id: String) : Serializable