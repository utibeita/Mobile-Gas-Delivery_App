package com.example.mystore.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mystore.R

class Welcome2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome2)

        val next2button = findViewById<Button>(R.id.next2_button)
        next2button.setOnClickListener{
            val i = Intent(this, WelcomeActivity::class.java)
            startActivity(i)
        }
    }
}