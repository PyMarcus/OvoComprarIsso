package com.example.ovocomprarisso

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ovocomprarisso.adapter.ItemAdapter
import com.example.ovocomprarisso.controllers.ItemController
import com.example.ovocomprarisso.databinding.ActivityItemsBinding
import com.example.ovocomprarisso.models.ItemEntity

class ItemsActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityItemsBinding
    private var selected: String = "alimento"
    private lateinit var itemController: ItemController
    private lateinit var adapter: ItemAdapter
    private var userId: Long = 0L
    private var items: MutableList<ItemEntity> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.itemController = ItemController(baseContext)
        this.userId = intent.getStringExtra("userId")!!.toLong()

        this.items.addAll(itemController.getAll(this.userId))

        this.binding.recycle.layoutManager = LinearLayoutManager(baseContext)
        this.adapter = ItemAdapter(items, this.itemController, baseContext)
        this.binding.recycle.adapter = adapter


        this.handleEvents()
    }

    private fun handleEvents(){
        this.binding.type.setOnClickListener(this)
        this.binding.add.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            this.binding.type.id -> showPopupMenu(v)
            this.binding.add.id -> addItem()
        }
    }

    private fun addItem(){
        val product = this.binding.product.text.toString()
        print("SELECCCT ${this.selected}")
        this.itemController.save(product, this.selected, this.userId)
        this.updateItemList()
        this.binding.product.text.clear()
        this.binding.type.clearFocus()
    }

    private fun updateItemList() {
        this.items.clear()
        this.items.addAll(itemController.getAll(this.userId))
        this.adapter.notifyDataSetChanged()
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
                else -> false
            }
        }

        popupMenu.show()
    }

}