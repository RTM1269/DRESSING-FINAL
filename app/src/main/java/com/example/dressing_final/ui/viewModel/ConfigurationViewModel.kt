package com.example.dressing02.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfigurationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is configuration Fragment"
    }
    val text: LiveData<String> = _text
}