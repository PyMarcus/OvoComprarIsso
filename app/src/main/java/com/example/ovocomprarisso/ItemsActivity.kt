package com.example.ovocomprarisso

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.PopupMenu
import com.example.ovocomprarisso.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityItemsBinding
    private lateinit var selected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.handleEvents()
    }

    private fun handleEvents(){
        this.binding.type.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            this.binding.type.id -> showPopupMenu(v)
        }
    }

    // menu options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_cleaning -> {
                this.binding.type.hint = "Limpeza"
                this.selected = "limpeza"
                true
            }
            R.id.menu_food -> {
                this.binding.type.hint = "Alimento"
                this.selected = "alimento"
                true
            }
            R.id.menu_drink -> {
                this.binding.type.hint = "Bebida"
                this.selected = "bebida"
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_items, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_cleaning -> {
                    this.binding.type.hint = "Limpeza"
                    true
                }
                R.id.menu_food -> {
                    this.binding.type.hint = "Alimento"
                    true
                }
                R.id.menu_drink -> {
                    this.binding.type.hint = "Bebida"
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

}