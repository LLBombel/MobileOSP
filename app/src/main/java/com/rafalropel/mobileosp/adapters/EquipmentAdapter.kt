package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.EquipmentItemBinding
import com.rafalropel.mobileosp.entities.EquipmentEntity

class EquipmentAdapter(
    private val equipmentItems: ArrayList<EquipmentEntity>,
    private val deleteListener: (id: Int) -> Unit
) :
    RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentAdapter.ViewHolder {
        return ViewHolder(
            EquipmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EquipmentAdapter.ViewHolder, position: Int) {
        val item = equipmentItems[position]
        holder.tvEquipmentName.text = item.equipmentName
        holder.ivDeleteEquipment.setOnClickListener {
            deleteListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return equipmentItems.size
    }

    class ViewHolder(binding: EquipmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val llEquipment = binding.llEquipment
        val tvEquipmentName = binding.tvEquipmentName
        val ivDeleteEquipment = binding.ivDeleteEquipment
        val ivEditEquipment = binding.ivEditEquipment

    }
}