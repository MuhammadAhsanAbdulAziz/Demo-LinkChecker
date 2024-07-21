package com.example.demolinkchecker.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demolinkchecker.R
import com.example.demolinkchecker.viewmodel.LinkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val linkViewModel by viewModels<LinkViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val linkTxt : EditText = findViewById(R.id.linkTxt)
        val hitBtn : Button = findViewById(R.id.hitBtn)
        val statusTxt : TextView = findViewById(R.id.statusTxt);

        hitBtn.setOnClickListener {
            val link = linkTxt.text.toString()
            linkViewModel.checkLink(link)
        }


    }
}