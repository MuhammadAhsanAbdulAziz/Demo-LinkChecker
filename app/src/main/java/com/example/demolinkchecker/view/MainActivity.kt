package com.example.demolinkchecker.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.demolinkchecker.R
import com.example.demolinkchecker.adapter.LinkAdapter
import com.example.demolinkchecker.utils.LinkCheckWorker
import com.example.demolinkchecker.viewmodel.CheckLinkViewModel
import com.example.demolinkchecker.viewmodel.LinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val linkViewModel by viewModels<LinkViewModel>()
    private lateinit var linkAdapter: LinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        linkAdapter = LinkAdapter()

        val list_view: RecyclerView = findViewById(R.id.list_view)

        list_view.adapter = linkAdapter


        val addBtn: Button = findViewById(R.id.addBtn)

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddLinkActivity::class.java))
        }

        bindObservers()
    }

    private fun bindObservers() {
        linkViewModel.allLinks.observe(this) { links ->
            linkAdapter.submitList(links)
            links.forEach {
                if (!it.status) {
                    setupLinkWork(it.name, Integer.parseInt(it.time))
                    linkViewModel.update(it)
                }

            }
        }
    }

    private fun setupLinkWork(linkUrl: String, intervalMinutes: Int) {
        val inputData = Data.Builder().putString("link_url", linkUrl)
            .putInt("interval_minutes", intervalMinutes).build()

        val workRequest = PeriodicWorkRequestBuilder<LinkCheckWorker>(
            intervalMinutes.toLong(),
            TimeUnit.MINUTES
        ).setInputData(inputData).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

}