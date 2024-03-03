package com.example.ovocomprarisso.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ovocomprarisso.R
import com.example.ovocomprarisso.controllers.ItemController
import com.example.ovocomprarisso.databinding.ItemListBinding
import com.example.ovocomprarisso.models.ItemEntity

class ItemAdapter(private var items: MutableList<ItemEntity>,private var itemController: ItemController,
                  private var context: Context):RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.
    ItemHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemAdapter.
        ItemHolder, position: Int
    ) {
        holder.bind(items[position], itemController, context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        if (position in 0 until items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    inner class ItemHolder(private var binding: ItemListBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(item: ItemEntity, itemController: ItemController, context: Context){
                binding.productItem.text = item.item
                val img = when (item.type) {
                    "alimento" -> R.drawable.food
                    "limpeza" -> R.drawable.clean
                    "bebida" -> R.drawable.wine
                    else -> 0
                }
                if(img != 0){
                    val drawable = ContextCompat.getDrawable(context, img)
                    this.binding.img.setImageDrawable(drawable)
                }
                binding.trash.setOnClickListener {
                    itemController.remove(item)
                    removeItem(adapterPosition)
                }
        }
    }

}