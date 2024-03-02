package com.example.ovocomprarisso

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.ovocomprarisso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), OnClickListener{

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.handleEvents()
    }

    private fun handleEvents(){
        this.binding.login.setOnClickListener(this)
        this.binding.register.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            this.binding.login.id -> this.login()
            this.binding.register.id -> this.openRegisterActivity()
        }
    }

    private fun login(){

    }

    private fun openRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}