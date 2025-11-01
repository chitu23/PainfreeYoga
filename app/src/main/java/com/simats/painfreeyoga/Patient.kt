package com.simats.painfreeyoga
import android.os.Parcel
import android.os.Parcelable



data class Patient(
    val name: String,
    val age: Int,
    val problem: String,
    val gender: String? = null,
    val contactNumber: String? = null,
    val recommendedVideo: String? = null,
    val suggestedYoga: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(problem)
        parcel.writeString(gender)
        parcel.writeString(contactNumber)
        parcel.writeString(recommendedVideo)
        parcel.writeString(suggestedYoga)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Patient> {
        override fun createFromParcel(parcel: Parcel): Patient {
            return Patient(parcel)
        }

        override fun newArray(size: Int): Array<Patient?> {
            return arrayOfNulls(size)
        }
    }
}
