package com.fiuba.bookbnb.ui.fragments.form.adapters

import android.os.Parcelable
import android.util.Log
import android.view.View
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.form.data.FormItemData
import com.fiuba.bookbnb.ui.fragments.form.data.HeaderFormData
import com.fiuba.bookbnb.ui.fragments.form.data.PhotoFormItemData
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bookbnb_photo_item.view.*

class PhotoAdapter(private var photoItems: MutableList<FormItemData>, headerFormData: HeaderFormData, private val updateImagesListener: UpdateImagesListener)
    : FormAdapter(photoItems, headerFormData, R.layout.bookbnb_photo_item) {

    override fun bindInputView(position: Int, itemView: View) {
        val formItem = photoItems[position] as PhotoFormItemData

        with(itemView) {
            text_label.text = context.getString(R.string.publish_image_label, position)
            delete_text.setOnClickListener {
                photoItems.removeAt(position)
                updateImagesListener.removePhoto(position-1)
                notifyItemRemoved(position)
                notifyDataSetChanged()

                val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(formItem.imgUrl)
                storageReference.delete()
                    .addOnSuccessListener {
                        Log.i("IMAGE", "Delete file successfully!")
                    }
                    .addOnFailureListener {
                        Log.i("IMAGE", "Error deleting file!")
                    }
            }

            Picasso.get()
                .load(formItem.imgUrl)
                .placeholder(R.drawable.ic_photoimgdefault)
                .into(image)
        }
    }

    fun updateAddPhoto(imgUrl: String) {
        photoItems.add(PhotoFormItemData(imgUrl))
        notifyItemInserted(photoItems.size - 1)
        notifyDataSetChanged()
    }

    interface UpdateImagesListener : Parcelable {
        fun removePhoto(position: Int)
        fun addPhoto(imgUrl: String)
    }

}