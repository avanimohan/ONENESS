package com.example.oneness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oneness.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_up)

        binding.btnSignUP.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        binding.tvHaveAccount.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }


    }
}