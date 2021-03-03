package com.fiuba.bookbnb.forms

import org.apache.commons.lang3.StringUtils
import java.util.*

class FormInputData(val inputField: FormInputType, content: String = StringUtils.EMPTY, val minDate: Date? = null, val maxDate: Date? = null) : FormInputBaseData(content)
