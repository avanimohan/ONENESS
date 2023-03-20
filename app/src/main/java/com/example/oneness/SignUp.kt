package com.example.oneness

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.core.content.ContextCompat
import com.example.oneness.databinding.ActivitySignUpBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable


@SuppressLint("CheckResult")
class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_up)

        //Fullname Validation
        val nameStream = RxTextView.textChanges(binding.etFullname)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        nameStream.subscribe {
            showNameExistAlert(it)
        }

        //EmailValidation
        val emailStream = RxTextView.textChanges(binding.etEmail)
            .skipInitialValue()
            .map { email ->
              !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailValidAlert(it)
        }

        //Username Validation
        val usernameStream = RxTextView.textChanges(binding.etUsername)
            .skipInitialValue()
            .map { username ->
                username.length < 6
            }
        usernameStream.subscribe {
            showTextMinimalAlert(it,"Username")
        }

        //Password Validation
        val passwordStream = RxTextView.textChanges(binding.etPassword)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showTextMinimalAlert(it,"Password")
        }

        //Confirm Password Validation
        val passwordConfirmStream = Observable.merge(
            RxTextView.textChanges(binding.etPassword)
                .skipInitialValue()
                .map { password ->
                    password.toString() != binding.etConfirmPassword.text.toString()
                },
                    RxTextView.textChanges(binding.etConfirmPassword)
                .skipInitialValue()
                        .map { confirmPassword ->
                            confirmPassword.toString() != binding.etPassword.text.toString()

                        })
        passwordConfirmStream.subscribe {
            showPasswordConfirmAlert(it)
        }

        //  Button Enable True or False

        val invalidFieldsStream = Observable.combineLatest(
            nameStream,
            emailStream,
            usernameStream,
            passwordStream,
            passwordConfirmStream,
            {nameInvalid: Boolean, emailInvalid: Boolean,usernameInvalid:Boolean, passwordInvalid:Boolean,passwordconfirmInvalid:Boolean ->
                !nameInvalid && !emailInvalid && !usernameInvalid && !passwordInvalid && !passwordconfirmInvalid
            })
        invalidFieldsStream.subscribe { isValid->
            if (isValid){
                binding.btnSignUP.isEnabled = true
                binding.btnSignUP.backgroundTintList = ContextCompat.getColorStateList(this,R.color.primary_color)
            }else{
                binding.btnSignUP.isEnabled = false
                binding.btnSignUP.backgroundTintList = ContextCompat.getColorStateList(this,android.R.color.darker_gray)

            }
        }


        //Click
        binding.btnSignUP.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        binding.tvHaveAccount.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }


    }

    private fun showNameExistAlert(isNotValid: Boolean){
        binding.etFullname.error = if (isNotValid) "Nama tidak boleh kosong!" else null
    }

    private fun showTextMinimalAlert(isNotValid: Boolean, text:String){
        if (text == "Username")
            binding.etUsername.error = if (isNotValid) "$text harus lebin dari 6 huruf!" else null
   else if (text == "Password")
            binding.etPassword.error = if (isNotValid) "$text harus lebin dari 8 huruf!" else null


    }
    private fun showEmailValidAlert(isNotValid: Boolean){
        binding.etEmail.error = if(isNotValid)"Email tidak valid" else null
    }

    private fun showPasswordConfirmAlert(isNotValid: Boolean){
        binding.etConfirmPassword.error = if(isNotValid)"Password tidak same" else null
    }

}
