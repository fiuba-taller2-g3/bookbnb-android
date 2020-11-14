package com.fiuba.bookbnb.forms.validators

enum class PostFixDefaultMsgValidator(val msg: String) {
    POSTFIX_NAME("un nombre"),
    POSTFIX_SURNAME("un apellido"),
    POSTFIX_PASSWORD("una contraseña"),
    POSTFIX_EMAIL("un correo electrónico"),
    POSTFIX_BIRTHDAY("una fecha de nacimiento")
}