package com.example.nestedrecyclerview02.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.nestedrecyclerview02.AnimalSection
import com.example.nestedrecyclerview02.R
import com.example.nestedrecyclerview02.adapter.base.BaseAdapter

class AnimalAdapter(
    items : List<AnimalSection.Animal> = emptyList()
)
    : BaseAdapter<AnimalSection.Animal>(R.layout.item_rv_animal,items){
    override fun bind(itemView: View, item: AnimalSection.Animal, position: Int, viewHolder: BaseViewHolderImp) {
        itemView.run {
            findViewById<TextView>(R.id.titleTextView).text = item.name
            val imageView = findViewById<ImageView>(R.id.imageView)
            Glide.with(itemView.context).load(item.image).placeholder(R.drawable.ic_launcher_background).into(imageView)
        }
    }
}