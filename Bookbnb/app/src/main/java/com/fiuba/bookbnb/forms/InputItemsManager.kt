package com.fiuba.bookbnb.forms

import android.content.Context
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.forms.inputFields.*
import com.fiuba.bookbnb.forms.validators.*
import com.fiuba.bookbnb.ui.fragments.publish.PublishInputUtils.MAX_LIMIT_BEDROOMS
import com.fiuba.bookbnb.ui.recyclerView.ContextMenuItemData
import com.fiuba.bookbnb.ui.utils.KeyboardType
import java.util.*

class InputItemsManager(private val context: Context, private val storeInputContent: (formInputData: FormInputData) -> Unit) {

    fun getInputModule(inputData: FormInputData) : InputFieldModule {
        return when(inputData.inputField) {
            FormInputType.EMAIL -> buildInputModule(R.string.email_field_label, buildInputFieldItem(inputData, EmailInputValidator(context)))
            FormInputType.PASSWORD_LOGIN -> buildPasswordInput(inputData, true)
            FormInputType.PASSWORD -> buildPasswordInput(inputData, false)
            FormInputType.NAME -> buildInputWithNotEmptyValidation(R.string.name_field_label, inputData, R.string.name_empty_msg_validation)
            FormInputType.SURNAME -> buildInputWithNotEmptyValidation(R.string.surname_field_label, inputData, R.string.surname_empty_msg_validation)
            FormInputType.BIRTH_DATE -> buildBirthDateInput(inputData, R.string.birthdate_register_description_text)
            FormInputType.BIRTH_DATE_EDIT -> buildBirthDateInput(inputData)
            FormInputType.GENDER -> buildInputModule(R.string.gender_field_label, RadioButtonInputField(context, inputData, storeInputContent, getGenderOptions()))
            FormInputType.PHONE_NUMBER -> buildInputModule(R.string.phone_number_field_label, buildInputFieldItem(inputData, PhoneNumberInputValidator(context), KeyboardType.NUMERIC))
            FormInputType.AVAILABILITY_TYPE -> buildAvailabilityTypeInputField(inputData)
            FormInputType.STAY_TYPE -> buildStayType(inputData, false)
            FormInputType.STAY_TYPE_SEARCH -> buildStayType(inputData, true)
            FormInputType.GUEST_QUANTITY -> buildInputModule(R.string.publish_guest_quantity_label, getQuantityControllerInput(R.string.publish_guest_quantity_item_label, inputData))
            FormInputType.BED_QUANTITY -> buildInputModule(R.string.publish_bed_quantity_label, getQuantityControllerInput(R.string.publish_bed_quantity_item_label, inputData))
            FormInputType.BATHROOM_QUANTITY -> buildInputModule(R.string.publish_bathroom_quantity_label, getQuantityControllerInput(R.string.publish_bathroom_quantity_item_label, inputData, maxLimit = 8))
            FormInputType.BEDROOM_QUANTITY -> buildInputModule(R.string.publish_bedroom_quantity_label, getQuantityControllerInput(R.string.publish_bedroom_quantity_item_label, inputData, 0, MAX_LIMIT_BEDROOMS))
            FormInputType.SINGLE_BEDS -> buildBedQuantityControllerInput(R.string.publish_single_bed_label, inputData)
            FormInputType.DOUBLE_BEDS -> buildBedQuantityControllerInput(R.string.publish_double_bed_label, inputData)
            FormInputType.CRIBS -> buildBedQuantityControllerInput(R.string.publish_cribs_bed_label, inputData)
            FormInputType.ADDRESS -> buildInputWithNotEmptyValidation(R.string.publish_location_address_label, inputData, R.string.address_empty_msg_validation)
            FormInputType.CITY -> buildInputWithNotEmptyValidation(R.string.publish_location_city_label, inputData, R.string.city_empty_msg_validation)
            FormInputType.COUNTRY -> buildInputWithNotEmptyValidation(R.string.publish_location_country_label, inputData, R.string.country_empty_msg_validation)
            FormInputType.APARTMENT -> buildInputModule(R.string.publish_location_apartment_label, buildInputFieldItem(inputData, DisabledInputValidator(context)))
            FormInputType.LOCATION -> buildInputModule(R.string.string_empty, LocationInputField(context))
            FormInputType.STAY_TITLE -> buildInputWithNotEmptyValidation(R.string.publish_stay_title_label, inputData, R.string.title_empty_msg_validation)
            FormInputType.STAY_DESCRIPTION -> buildInputModule(R.string.publish_stay_description_label, EditTextMultilineInputField(context, inputData, storeInputContent, NotEmptyInputValidator(context, R.string.subtitle_empty_msg_validation)))
            FormInputType.START_DATE -> buildRangeDatePickerInputField(R.string.publish_range_date_picker_start_label, inputData)
            FormInputType.END_DATE -> buildRangeDatePickerInputField(R.string.publish_range_date_picker_end_label, inputData)
            FormInputType.MIN_PRICE -> buildPriceSearchInputField(R.string.search_min_price_label, inputData)
            FormInputType.MAX_PRICE -> buildPriceSearchInputField(R.string.search_max_price_label, inputData)
            FormInputType.SEARCH_CITY -> buildInputModule(R.string.publish_location_city_label, buildInputFieldItem(inputData, DisabledInputValidator(context)))
            FormInputType.SEARCH_START_DATE -> buildRangeDatePickerInputFieldWithoutValidation(R.string.publish_range_date_picker_start_label, inputData)
            FormInputType.SEARCH_END_DATE -> buildRangeDatePickerInputFieldWithoutValidation(R.string.publish_range_date_picker_end_label, inputData)
            FormInputType.BOOKING_START_DATE -> buildRangeDatePickerInputFieldWithLimits(R.string.publish_range_date_picker_start_label, inputData)
            FormInputType.BOOKING_END_DATE -> buildRangeDatePickerInputFieldWithLimits(R.string.publish_range_date_picker_end_label, inputData)
            FormInputType.BOOKING_INFO -> buildInputModule(R.string.string_empty, BookingInfoField(context))
            FormInputType.PRICE -> buildInputModule(R.string.publish_price_label, buildInputFieldItem(inputData, NotEmptyInputValidator(context, R.string.price_empty_msg_validation), KeyboardType.NUMERIC))
        }
    }

    private fun buildPriceSearchInputField(labelRes: Int, inputData: FormInputData) : InputFieldModule {
        return buildInputModule(labelRes, buildInputFieldItem(inputData, DisabledInputValidator(context), KeyboardType.NUMERIC))
    }

    private fun buildRangeDatePickerInputFieldWithLimits(labelRes: Int, inputData: FormInputData): InputFieldModule {
        return buildRangeDatePickerInputField(labelRes, inputData, minDate = inputData.minDate, maxDate = inputData.maxDate)
    }

    private fun buildRangeDatePickerInputFieldWithoutValidation(labelRes: Int, inputData: FormInputData) : InputFieldModule {
        return buildRangeDatePickerInputField(labelRes, inputData, DisabledInputValidator(context), showClearButton = true)
    }

    private fun buildRangeDatePickerInputField(labelRes: Int, inputData: FormInputData,
                                               validator: InputValidator = NotEmptyInputValidator(context, R.string.date_picker_empty_msg_validation),
                                               showClearButton: Boolean = false,
                                               minDate: Date? = Date(), maxDate: Date? = null) : InputFieldModule {
        return buildInputModule(labelRes, DatePickerInputField(context, inputData, R.string.title_date_picker_dialog, storeInputContent, minDate, maxDate, showClearButton, validator))
    }

    private fun getGenderOptions() = Pair(context.getString(R.string.edit_info_profile_male_genre), context.getString(R.string.edit_info_profile_female_genre))

    private fun buildBedQuantityControllerInput(labelRes: Int, inputData: FormInputData) : InputFieldModule {
        return buildInputModule(R.string.string_empty, QuantityControllerInputField(context, labelRes, inputData, storeInputContent, 0, 5))
    }

    private fun getQuantityControllerInput(labelRes: Int, inputData: FormInputData, minLimit: Int = 1, maxLimit: Int = 16) : AbstractInputFieldItem {
        return QuantityControllerInputField(context, labelRes, inputData, storeInputContent, minLimit, maxLimit)
    }

    private fun buildBirthDateInput(inputData: FormInputData, descriptionRes: Int? = null): InputFieldModule {
        return buildInputModule(R.string.birthdate_field_label, DatePickerInputField(context, inputData, R.string.title_birth_date_picker_dialog, storeInputContent, maxDate = Date()), descriptionRes)
    }

    private fun buildInputWithNotEmptyValidation(labelRes: Int, inputData: FormInputData, msgEmptyValidatorRes: Int): InputFieldModule {
        return buildInputModule(labelRes, buildInputFieldItem(inputData, NotEmptyInputValidator(context, msgEmptyValidatorRes)))
    }

    private fun buildInputFieldItem(inputData: FormInputData, validator: InputValidator, keyboardType: KeyboardType = KeyboardType.ALPHANUMERIC): EditTextInputFieldItem {
        return EditTextInputFieldItem(context, inputData, storeInputContent, validator, keyboardType)
    }

    private fun buildPasswordInput(inputData: FormInputData, isFormLogin: Boolean): InputFieldModule {
        val passValidator = if (isFormLogin) NotEmptyInputValidator(context, R.string.pass_empty_msg_validation) else PassInputValidator(context)
        return buildInputModule(R.string.pass_field_label, buildInputFieldItem(inputData, passValidator, KeyboardType.ALPHANUMERIC_PASSWORD))
    }

    private fun buildInputModule(labelRes: Int, inputFieldItem: AbstractInputFieldItem, descriptionRes: Int? = null) : InputFieldModule {
        return with(context) { InputFieldModule(this, getString(labelRes), inputFieldItem, descriptionRes) }
    }

    private fun buildAvailabilityTypeInputField(inputData: FormInputData) : InputFieldModule {
        with(context) {
            val itemList = arrayOf(
                ContextMenuItemData(getString(R.string.publish_entire_stay_text_item), getString(R.string.publish_entire_stay_text_description)),
                ContextMenuItemData(getString(R.string.publish_private_room_text_item), getString(R.string.publish_private_room_text_description)),
                ContextMenuItemData(getString(R.string.publish_shared_room_text_item), getString(R.string.publish_shared_room_text_description))
            )
            val title = getString(R.string.publish_availability_type_label)

            return buildInputModule(R.string.publish_availability_type_label, buildContextMenuInputField(title, itemList, inputData))
        }
    }

    private fun buildStayType(inputData: FormInputData, isSearchField: Boolean) : InputFieldModule {
        with(context) {
            val itemList = arrayOf(ContextMenuItemData(getString(R.string.publish_home_type_text_item), getString(R.string.publish_home_type_text_description)),
                ContextMenuItemData(getString(R.string.publish_hotel_type_text_item), getString(R.string.publish_hotel_type_text_description)))
            val title = getString(if (isSearchField) R.string.search_stay_type_label else R.string.publish_stay_type_label)
            val inputLabel = if (isSearchField) R.string.search_stay_type_label else R.string.publish_availability_type_label

            return buildInputModule(inputLabel, buildContextMenuInputField(title, itemList, inputData))
        }
    }

    private fun buildContextMenuInputField(title: String, itemList: Array<ContextMenuItemData>, inputData: FormInputData): ContextMenuInputField {
        return ContextMenuInputField(context, inputData, storeInputContent, title, itemList, DisabledInputValidator(context))
    }
}