package com.fiuba.bookbnb.domain.register

import java.io.Serializable

data class RegisterRequest(val name: String, val surname: String, val email: String,
                           val password: String, val type: String, val gender: String,
                           val phoneNumber: String, val birthDate: String) : Serializable