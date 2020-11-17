package com.fiuba.bookbnb.forms

import org.apache.commons.lang3.StringUtils

enum class InputField(private val postfixTextEmptyValidator: String) {
    NAME("un nombre"),
    SURNAME("un apellido"),
    EMAIL("un correo electrónico"),
    PASSWORD("una contraseña"),
    BIRTHDATE("una fecha de nacimiento"),
    PHONE_NUMBER("un número de teléfono"),
    GENDER(StringUtils.EMPTY),
    PROFILE_TYPE(StringUtils.EMPTY);

    fun getTextEmptyValidator() = "Ingrese $postfixTextEmptyValidator"
}