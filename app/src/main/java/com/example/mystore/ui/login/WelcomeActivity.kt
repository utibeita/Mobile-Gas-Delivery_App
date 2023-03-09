package com.example.mystore.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mystore.MainActivity
import com.example.mystore.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val nextbutton = findViewById<Button>(R.id.next_button)
        nextbutton.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}