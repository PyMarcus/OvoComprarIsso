package com.example.ovocomprarisso

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.ovocomprarisso.controllers.UserController
import com.example.ovocomprarisso.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity(), OnClickListener{

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.userController = UserController(this)
        this.handleEvents()
    }

    private fun handleEvents(){
        this.binding.login.setOnClickListener(this)
        this.binding.register.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            this.binding.login.id -> this.openLoginActivity()
            this.binding.register.id -> this.register()
        }
    }

    private fun register(){
        val username = this.binding.username.editText?.text.toString()
        val password = this.binding.password.editText?.text.toString()

        if(userController.save(username, password)){
            this.openLoginActivity()
        }else{
            this.showAlert("Error", "Não é possivel cadastrar esse usuário!")
        }


    }

    private fun openLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}