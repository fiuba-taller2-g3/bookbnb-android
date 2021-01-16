package com.fiuba.bookbnb.ui.fragments.dialogs

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.bookbnb_upload_photo_dialog_fragment.view.*
import java.io.IOException
import java.util.*


class UploadPhotoDialog : DialogBaseFragment() {

    private var filePath: Uri? = null
    private var uploadPhotoView : View? = null

    private val navArguments by navArgs<UploadPhotoDialogArgs>()
    private val updateImagesListener by lazy { navArguments.updateImagesListener }

    override fun getBodyView(): View? {
        uploadPhotoView = View.inflate(context, R.layout.bookbnb_upload_photo_dialog_fragment, null)

        uploadPhotoView?.let {
            it.btn_choose.setOnClickListener { chooseImage() }
            disableButton(it.btn_upload)
            it.btn_upload.setOnClickListener { uploadImage() }
            it.close_button.setOnClickListener { dismiss() }
        }

        return uploadPhotoView
    }

    private fun disableButton(button: View) {
        button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorTextInputFieldDisabled))
        button.isEnabled = false
    }

    private fun enableButton(button: View) {
        button.setBackgroundResource(R.drawable.button_ripple)
        button.isEnabled = true
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = IMAGE_DIR
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, getString(R.string.publish_select_image_label)), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                val bitmap = if (android.os.Build.VERSION.SDK_INT >= 29) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, filePath!!))
                } else MediaStore.Images.Media.getBitmap(requireContext().contentResolver, filePath)
                uploadPhotoView?.let {
                    it.img_view.setImageBitmap(bitmap)
                    it.img_view.isVisible = true
                    enableButton(it.btn_upload)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        if (filePath != null) {
            val ref = FirebaseStorage.getInstance().reference.child(PATH + UUID.randomUUID().toString())
            setDialogCancelable(false)
            ref.putFile(filePath!!)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { downloadPhotoUrl ->
                        updateImagesListener.addPhoto(downloadPhotoUrl.toString())
                        Toast.makeText(requireContext(), getString(R.string.publish_upload_image_success_label), Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                }
                .addOnFailureListener { e ->
                    setDialogCancelable(true)
                    setLoadingStatus(false)
                    Toast.makeText(requireContext(), "Failed " + e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    uploadPhotoView?.let {
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        it.progress_label.text = getString(R.string.publish_upload_progress_label, progress.toInt())
                        setLoadingStatus(true)
                    }
                }
        }
    }

    private fun setLoadingStatus(isLoading: Boolean) {
        uploadPhotoView?.let {
            it.progress_container.isVisible = isLoading
            it.layout_buttons.isVisible = !isLoading
            it.close_button.isVisible = !isLoading
        }
    }

    private fun setDialogCancelable(isCancelable: Boolean) {
        dialog?.let {
            it.setCancelable(isCancelable)
            it.setCanceledOnTouchOutside(isCancelable)
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 71
        private const val IMAGE_DIR = "image/*"
        private const val PATH = "images/"
    }

}