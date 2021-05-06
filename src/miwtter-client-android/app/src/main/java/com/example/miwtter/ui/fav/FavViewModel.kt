package com.example.miwtter.ui.fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is fav Fragment"
    }
    val text: LiveData<String> = _text
}