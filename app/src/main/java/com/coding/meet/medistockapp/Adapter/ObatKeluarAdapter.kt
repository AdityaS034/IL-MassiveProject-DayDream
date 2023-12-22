package com.coding.meet.medistockapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.meet.medistockapp.DetailObatKeluar
import com.coding.meet.medistockapp.ObatKeluarData
import com.coding.meet.medistockapp.databinding.ListDataObatKeluarBinding

class ObatKeluarAdapter(
    private val listObatKeluar:ArrayList<ObatKeluarData>,
    private val context: Context
):RecyclerView.Adapter<ObatKeluarAdapter.ObatKeluarViewHolder>() {

    inner class ObatKeluarViewHolder(item: ListDataObatKeluarBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(obatKeluarData: ObatKeluarData){
            with(binding){
                txtObat.text = obatKeluarData.nama
                txtJml.text = obatKeluarData.jml
                txtKeluar.text = obatKeluarData.keluar

                cvData.setOnClickListener {
                    var i = Intent(context, DetailObatKeluar::class.java).apply {
                        putExtra("nobat",obatKeluarData.nobat)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObatKeluarViewHolder {
        return ObatKeluarViewHolder(ListDataObatKeluarBinding.inflate(LayoutInflater.from(parent.context),
            parent, false
            ))
    }

    override fun getItemCount(): Int = listObatKeluar.size


    override fun onBindViewHolder(holder: ObatKeluarViewHolder, position: Int) {
        holder.bind(listObatKeluar[position])
    }

}