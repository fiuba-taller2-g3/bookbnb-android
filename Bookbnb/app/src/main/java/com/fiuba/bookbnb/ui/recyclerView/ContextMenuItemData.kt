package com.fiuba.bookbnb.ui.recyclerView

import android.os.Parcel
import android.os.Parcelable

data class ContextMenuItemData(val title: String, val subtitle: String? = null) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(subtitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContextMenuItemData> {
        override fun createFromParcel(parcel: Parcel): ContextMenuItemData {
            return ContextMenuItemData(parcel)
        }

        override fun newArray(size: Int): Array<ContextMenuItemData?> {
            return arrayOfNulls(size)
        }
    }
}