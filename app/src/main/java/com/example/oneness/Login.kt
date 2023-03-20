package com.example.oneness

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.oneness.databinding.ActivityLoginBinding
import com.example.oneness.databinding.ActivityMainBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

@SuppressLint("CheckResult")
class Login : AppCompatActivity() {

   private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        //Username Validation
        val usernameStream = RxTextView.textChanges(binding.etEmail)
            .skipInitialValue()
            .map { username ->
                username.isEmpty()
            }
        usernameStream.subscribe {
            showTextMinimalAlert(it,"Email/Username")
        }

        //Password Validation
        val passwordStream = RxTextView.textChanges(binding.etPassword)
            .skipInitialValue()
            .map { password ->
                password.isEmpty()
            }
        passwordStream.subscribe {
            showTextMinimalAlert(it,"Password")
        }

        //  Button Enable True or False

        val invalidFieldsStream = Observable.combineLatest(
            usernameStream,
            passwordStream,
            {usernameInvalid:Boolean, passwordInvalid:Boolean,->
                !usernameInvalid && !passwordInvalid
            })
        invalidFieldsStream.subscribe { isValid->
            if (isValid){
                binding.btnLogin.isEnabled = true
                binding.btnLogin.backgroundTintList = ContextCompat.getColorStateList(this,R.color.primary_color)
            }else{
                binding.btnLogin.isEnabled = false
                binding.btnLogin.backgroundTintList = ContextCompat.getColorStateList(this,android.R.color.darker_gray)

            }
        }


//Click


        binding.btnLogin.setOnClickListener{
             startActivity(Intent(this,HomeActivity::class.java))
        }
        binding.tvHaventAccount.setOnClickListener{
           startActivity(Intent(this,SignUp::class.java))

        }
    }
    private fun showTextMinimalAlert(isNotValid: Boolean, text:String){
        if (text == "Email/Username")
            binding.etEmail.error = if (isNotValid) "$text tidak boleh kosong " else null
        else if (text == "Password")
            binding.etPassword.error = if (isNotValid) "$text tidak boleh kosong " else null


    }
}