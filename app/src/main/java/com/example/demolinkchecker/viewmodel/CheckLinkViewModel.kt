package com.example.demolinkchecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demolinkchecker.repository.CheckLinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckLinkViewModel @Inject constructor(private val checkLinkRepository: CheckLinkRepository) : ViewModel() {

    fun checkLink(url:String) {
        viewModelScope.launch {
            checkLinkRepository.checkLink(url)
        }
    }

}