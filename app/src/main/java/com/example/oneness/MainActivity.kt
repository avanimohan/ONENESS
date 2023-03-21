package com.example.oneness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import com.example.oneness.databinding.ActivityHomeBinding
import com.example.oneness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    binding.btnLogin.setOnClickListener{
        startActivity(Intent(this,Login::class.java))
    }
    binding.btnRegister.setOnClickListener{
        startActivity(Intent(this,SignUp::class.java))
    }

    }}


