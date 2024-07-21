package com.example.demolinkchecker.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demolinkchecker.data.local.LinkDatabase
import com.example.demolinkchecker.data.local.LinkModel
import com.example.demolinkchecker.repository.LinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinkViewModel @Inject constructor(
    private val repository: LinkRepository
) : ViewModel() {

    val allLinks: LiveData<List<LinkModel>> = repository.allLinks

    fun insert(link: LinkModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(link)
        }
    }

    fun update(link: LinkModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(link.copy(isStarted = true))
        }
    }

    fun updateStatus(id: Int, status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStatus(id, status)
        }
    }

    fun delete(link: LinkModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(link)
        }
    }
}