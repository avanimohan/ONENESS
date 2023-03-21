package com.example.oneness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oneness.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)

 auth = FirebaseAuth.getInstance()
    }


}