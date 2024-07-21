package com.example.demolinkchecker.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demolinkchecker.R
import com.example.demolinkchecker.data.local.LinkModel
import com.example.demolinkchecker.viewmodel.CheckLinkViewModel
import com.example.demolinkchecker.viewmodel.LinkViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLinkActivity : AppCompatActivity() {

    private val linkViewModel by viewModels<LinkViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_link)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val websiteTxt : TextInputEditText = findViewById(R.id.websiteTxt)
        val timeTxt : TextInputEditText = findViewById(R.id.timeTxt)
        val addBtn : Button = findViewById(R.id.addLinkBtn)


        addBtn.setOnClickListener {
            val website = websiteTxt.text.toString()
            val time = timeTxt.text.toString()

            val link = LinkModel(name = website, time = time, status = true, isStarted = false)

            linkViewModel.insert(link)
        }
    }
}