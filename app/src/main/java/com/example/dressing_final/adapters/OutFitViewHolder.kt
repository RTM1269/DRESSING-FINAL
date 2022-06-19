package com.example.dressing_v00.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dressing_final.databinding.OutfitCardTemplateBinding
import com.example.dressing_v00.models.setModel.OutFit
import com.squareup.picasso.Picasso

class OutFitViewHolder(view: View): RecyclerView.ViewHolder(view) {
    //Bindeo la información con la vista recibida en el ViewHolder. (En este caso la vista será la de FragmentSet)
    val binding = OutfitCardTemplateBinding.bind(view)
    fun render(itemModel: OutFit) {
        binding.tvTitle.text = itemModel.nameSet
        //binding.tvDescription.text = itemModel.type
        Picasso.get().load(itemModel.photo).into(binding.ivSet)
        //Control de clicks
        //1-En la foto
        binding.ivSet.setOnClickListener {
            Toast.makeText(binding.ivSet.context,itemModel.type,Toast.LENGTH_SHORT).show()
        }
        //2-En el item
        itemView.setOnClickListener {
            Toast.makeText(binding.ivSet.context,itemModel.nameSet,Toast.LENGTH_SHORT).show()
        }
    }

}
