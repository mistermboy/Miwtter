package com.example.miwtter.parcel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tweet(val date: String, val description: String, val min: Float, val max: Float) : Parcelable