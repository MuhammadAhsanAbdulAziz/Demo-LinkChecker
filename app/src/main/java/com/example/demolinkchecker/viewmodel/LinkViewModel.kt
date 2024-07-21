package com.example.demolinkchecker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demolinkchecker.repository.LinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(private val linkRepository: LinkRepository) : ViewModel() {

    fun checkLink(url:String) {
        viewModelScope.launch {
            linkRepository.checkLink(url)
        }
    }

}