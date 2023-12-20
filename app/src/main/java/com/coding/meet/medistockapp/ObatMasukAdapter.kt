package com.coding.meet.medistockapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.meet.medistockapp.databinding.ListDataObatMasukBinding

class ObatMasukAdapter(
    private val listObatMasuk:ArrayList<ObatMasukData>,
    private val context: Context
):RecyclerView.Adapter<ObatMasukAdapter.ObatMasukViewHolder>() {

    inner class ObatMasukViewHolder(item:ListDataObatMasukBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(obatMasukData: ObatMasukData){
            with(binding){
                txtObat.text = obatMasukData.nama
                txtJml.text = obatMasukData.jml
                txtMasuk.text = obatMasukData.masuk

                cvData.setOnClickListener {
                    var i = Intent(context, DetailObatMasuk::class.java).apply {
                        putExtra("nobat",obatMasukData.nobat)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatMasukViewHolder {
        return ObatMasukViewHolder(ListDataObatMasukBinding.inflate(LayoutInflater.from(parent.context),
            parent, false
            ))
    }

    override fun getItemCount(): Int = listObatMasuk.size


    override fun onBindViewHolder(holder: ObatMasukViewHolder, position: Int) {
        holder.bind(listObatMasuk[position])
    }

}