package com.example.dressing_final.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dressing_final.ClothesModel.Clothing
import com.example.dressing_final.databinding.PartOfClothesTemplateBinding
import com.squareup.picasso.Picasso

class ClothingViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = PartOfClothesTemplateBinding.bind(view)
    fun render(itemModel: Clothing){
        // Seteamos la foto correspondiente del item.
        Picasso.get().load(itemModel.img).into(binding.ivClothing)
    }
}
