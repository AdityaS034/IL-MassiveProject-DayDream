package com.coding.meet.medistockapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.meet.medistockapp.DetailTotalObat
import com.coding.meet.medistockapp.TotalObatData
import com.coding.meet.medistockapp.databinding.ListTotalObatBinding

class TotalObatAdapter(
    private val listTotalObat:ArrayList<TotalObatData>,
    private val context: Context
):RecyclerView.Adapter<TotalObatAdapter.TotalObatViewHolder>() {

    inner class TotalObatViewHolder(item: ListTotalObatBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(TotalObatData: TotalObatData){
            with(binding){
                txtObat.text = TotalObatData.nama
                txtAwal.text = TotalObatData.stokawal
                txtAkhir.text = TotalObatData.stokakhir

                cvData.setOnClickListener {
                    var i = Intent(context, DetailTotalObat::class.java).apply {
                        putExtra("nobat",TotalObatData.nobat)
                    }
                    context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalObatViewHolder {
        return TotalObatViewHolder(ListTotalObatBinding.inflate(LayoutInflater.from(parent.context),
            parent, false
            ))
    }

    override fun getItemCount(): Int = listTotalObat.size


    override fun onBindViewHolder(holder: TotalObatViewHolder, position: Int) {
        holder.bind(listTotalObat[position])
    }

}