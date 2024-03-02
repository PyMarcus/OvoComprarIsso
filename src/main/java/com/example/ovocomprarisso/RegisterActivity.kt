package com.example.ovocomprarisso

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.ovocomprarisso.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity(), OnClickListener{

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.handleEvents()
    }

    private fun handleEvents(){
        this.binding.login.setOnClickListener(this)
        this.binding.register.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            this.binding.login.id -> this.register()
            this.binding.register.id -> this.openLoginActivity()
        }
    }

    private fun register(){

    }

    private fun openLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}