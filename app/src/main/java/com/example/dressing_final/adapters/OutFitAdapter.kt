package com.example.dressing_v00.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dressing_final.R
import com.example.dressing_v00.models.setModel.OutFit

class OutFitAdapter(private val setList: List<OutFit>): RecyclerView.Adapter<OutFitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutFitViewHolder {
        //Devolverá el conjunto que acabemos de crear al setViewHolder, por cada objeto que haya en el 'setList'.
        val layoutInflater = LayoutInflater.from(parent.context)
        return OutFitViewHolder(layoutInflater.inflate(R.layout.sets_card_template,parent,false))
    }

    override fun onBindViewHolder(holder: OutFitViewHolder, position: Int) {
        val item = setList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = setList.size
}