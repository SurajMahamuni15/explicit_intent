package com.example.intentdemo

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor() : ViewModel() {

    private var uri = MutableLiveData<Uri>()
    val uriPath: LiveData<Uri> get() = uri

    fun setNewUri(uri: Uri) {
        this.uri.postValue(uri)
//        Log.e("uri", this.uri.toString())
    }
}