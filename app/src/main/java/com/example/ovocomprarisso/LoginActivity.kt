package com.example.ovocomprarisso

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.ovocomprarisso.controllers.UserController
import com.example.ovocomprarisso.databinding.ActivityLoginBinding
import com.example.ovocomprarisso.models.UserEntity

class LoginActivity : AppCompatActivity(), OnClickListener{

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityLoginBinding.inflate(layoutInflater)
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
            this.binding.login.id -> this.login()
            this.binding.register.id -> this.openRegisterActivity()
        }
    }

    private fun login(){

        val username = this.binding.username.editText?.text.toString()
        val password = this.binding.password.editText?.text.toString()

        if(this.userController.getUserLogin(username, password)){
            val intent = Intent(this, ItemsActivity::class.java)
            val user: UserEntity? = this.userController.getUser(username)
            if(user?.id != null){
                intent.putExtra("userId", user.id.toString())
            }
            startActivity(intent)
        }else{
            this.showAlert("Error", "Verifique seus dados e tente novamente!")
        }
    }

    private fun openRegisterActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
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