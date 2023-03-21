package com.example.oneness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.Toast
import com.example.oneness.databinding.ActivityHomeBinding
import com.example.oneness.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


    binding.btnLogin.setOnClickListener{
        startActivity(Intent(this,Login::class.java))
    }
    binding.btnRegister.setOnClickListener{
        startActivity(Intent(this,SignUp::class.java))
    }

    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
Intent(this, HomeActivity::class.java).also{
    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(it)

             }
        }
    }

}


