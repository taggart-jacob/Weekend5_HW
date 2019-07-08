package com.example.weekend5_hw

import android.os.Parcel
import android.os.Parcelable

class EmployeeModel(var empFirstName: String? = null, var empLastName: String? = null, var empAddress: String? = null, var empCity: String? = null, var empState: String? = null,
                     var empZip: String? =null, var empTaxId: String? = null, var empPosition: String? = null, var empDepartment: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(empFirstName)
        parcel.writeString(empLastName)
        parcel.writeString(empAddress)
        parcel.writeString(empCity)
        parcel.writeString(empState)
        parcel.writeString(empZip)
        parcel.writeString(empTaxId)
        parcel.writeString(empPosition)
        parcel.writeString(empDepartment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeeModel> {
        override fun createFromParcel(parcel: Parcel): EmployeeModel {
            return EmployeeModel(parcel)
        }

        override fun newArray(size: Int): Array<EmployeeModel?> {
            return arrayOfNulls(size)
        }
    }
}