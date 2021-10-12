package com.example.ey.ui

import androidx.lifecycle.ViewModel
import com.example.ey.api.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel(){

}
