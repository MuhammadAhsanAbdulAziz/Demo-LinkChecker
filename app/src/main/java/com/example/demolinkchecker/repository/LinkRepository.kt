package com.example.demolinkchecker.repository

import androidx.lifecycle.LiveData
import com.example.demolinkchecker.data.local.LinkDao
import com.example.demolinkchecker.data.local.LinkModel
import javax.inject.Inject

class LinkRepository @Inject constructor(private val linkDao: LinkDao) {


    val allLinks: LiveData<List<LinkModel>> = linkDao.getAllLink()

    suspend fun insert(link: LinkModel) {
        linkDao.insertLink(link)
    }

    suspend fun update(link: LinkModel) {
        linkDao.updateLink(link)
    }

    suspend fun updateStatus(id: Int, status: Boolean) {
        linkDao.updateStatus(id, status)
    }

    suspend fun delete(link: LinkModel) {
        linkDao.deleteLink(link)
    }
}