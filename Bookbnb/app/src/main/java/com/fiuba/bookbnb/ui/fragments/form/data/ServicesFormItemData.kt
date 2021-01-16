package com.fiuba.bookbnb.ui.fragments.form.data

import com.fiuba.bookbnb.ui.fragments.form.services.Services

class ServicesFormItemData(val service: Services, val label: String, val description: String? = null, var isChecked: Boolean) : FormItemData(InputViewType.INPUT)