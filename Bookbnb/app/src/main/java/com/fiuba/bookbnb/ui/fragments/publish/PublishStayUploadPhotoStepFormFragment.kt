package com.fiuba.bookbnb.ui.fragments.publish

import android.app.AlertDialog
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.core.view.isVisible
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.dialogs.UploadPhotoDialogFragmentDirections
import com.fiuba.bookbnb.ui.fragments.form.FormListFragment
import com.fiuba.bookbnb.ui.fragments.form.adapters.FormAdapter
import com.fiuba.bookbnb.ui.fragments.form.adapters.PhotoAdapter
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.fragments.form.data.InputViewType
import com.fiuba.bookbnb.ui.fragments.form.data.PhotoFormItemData
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_form_list_fragment.*


class PublishStayUploadPhotoStepFormFragment() : FormListFragment(), PhotoAdapter.UpdateImagesListener {

    constructor(parcel: Parcel) : this()

    override fun getTitleTextRes(): Int = R.string.publish_upload_photo_label
    override fun getSubtitleTextRes(): Int = R.string.publish_upload_photo_description
    override fun getButtonTextRes(): Int = R.string.next_button_text

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        empty_list_text.text = getString(R.string.publish_images_empty_text)
        checkEmptyList()
        add_photo_button.isVisible = true
        add_photo_button.setOnClickListener {
            if (formViewModel.photosUrls.size < MAX_IMAGES) {
                NavigationManager.showDialog { UploadPhotoDialogFragmentDirections.showUploadPhotoDialog(this) }
            } else AlertDialog.Builder(context).setMessage("No se permite subir más imágenes, elimine alguna de las imágenes para poder subir otra.").show()
        }
    }

    override fun getFormItems(): List<FormItemData> {
        val formInputItems = ArrayList<FormItemData>()
        formInputItems.add(FormItemData(InputViewType.HEADER))
        formViewModel.photosUrls.forEach { photoUrl ->
            formInputItems.add(photoUrl)
        }

        return formInputItems
    }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun getFormAdapter(): FormAdapter {
        return PhotoAdapter(getFormItems().toMutableList(), HeaderFormData(getString(getTitleTextRes()), getString(getSubtitleTextRes())), this)
    }

    override fun removePhoto(position: Int) {
        formViewModel.photosUrls.removeAt(position)
        checkEmptyList()
    }

    override fun addPhoto(imgUrl: String) {
        formViewModel.photosUrls.add(PhotoFormItemData(imgUrl))
        (form_container.adapter as PhotoAdapter).updateAddPhoto(imgUrl)
        checkEmptyList()
    }

    private fun checkEmptyList() {
        empty_list_text.isVisible = formViewModel.photosUrls.isEmpty()
    }

    override fun setActionEventButton() {
        NavigationManager.moveForward(PublishStayUploadPhotoStepFormFragmentDirections.actionPublishStayUploadPhotoStepFormFragmentToPublishStayRangeDatePickerStepFormFragment())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // Do Nothing
    }

    companion object CREATOR : Parcelable.Creator<PublishStayUploadPhotoStepFormFragment> {
        override fun createFromParcel(parcel: Parcel): PublishStayUploadPhotoStepFormFragment {
            return PublishStayUploadPhotoStepFormFragment(parcel)
        }

        override fun newArray(size: Int): Array<PublishStayUploadPhotoStepFormFragment?> {
            return arrayOfNulls(size)
        }

        private const val MAX_IMAGES = 10
    }

}