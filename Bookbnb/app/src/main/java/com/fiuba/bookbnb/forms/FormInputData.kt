package com.fiuba.bookbnb.forms

import org.apache.commons.lang3.StringUtils

class FormInputData(val inputField: FormInputType, content: String = StringUtils.EMPTY) : FormInputBaseData(content)
