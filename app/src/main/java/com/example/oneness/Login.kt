package com.example.oneness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oneness.databinding.ActivityLoginBinding
import com.example.oneness.databinding.ActivityMainBinding

class Login : AppCompatActivity() {

   private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

binding.btnLogin.setOnClickListener{
    startActivity(Intent(this,HomeActivity::class.java))
}
binding.tvHaventAccount.setOnClickListener{
startActivity(Intent(this,SignUp::class.java))

}
    }
}