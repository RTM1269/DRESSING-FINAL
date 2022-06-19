package com.example.dressing_final.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dressing_final.ClothesModel.Closet
import com.example.dressing_final.ClothesModel.Clothing
import com.example.dressing_final.databinding.ClosetCardTemplateBinding
import com.example.dressing_final.databinding.PartOfClothesTemplateBinding
import com.squareup.picasso.Picasso

class ClosetViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ClosetCardTemplateBinding.bind(view)
    fun render(itemModel: Closet){
        binding.tvNameCloset.text = itemModel.name
        binding.tvPlaceCloset.text = itemModel.house
        // Seteamos la foto correspondiente del item.
        Picasso.get().load(itemModel.img).into(binding.ivCloset)
    }
}
