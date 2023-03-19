package com.example.oneness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oneness.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)
    }
}