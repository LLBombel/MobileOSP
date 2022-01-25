package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.EquipmentItemBinding
import com.rafalropel.mobileosp.entities.EquipmentEntity

class EquipmentAdapter(
    private val equipmentItems: ArrayList<EquipmentEntity>,
    private val deleteListener: (id: Int) -> Unit,
    private val updateListener: (id: Int) -> Unit
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
        holder.tvEquipmentOwnName.text = item.equipmentOwnName
        holder.tvEquipmentAmount.text = item.equipmentAmount
        holder.tvEquipmentStorageLocation.text = item.equipmentStorageLocation
        holder.tvEquipmentYear.text = item.equipmentYear
        holder.tvEquipmentSerialNumber.text = item.equipmentSerialNumber
        holder.tvEquipmentInventoryNumber.text = item.equipmentInventoryNumber
        holder.tvEquipmentTechnicalReviewDate.text = item.equipmentTechnicalReviewDate
        holder.tvEquipmentEnginePower.text = item.equipmentEnginePower


        holder.ivDeleteEquipment.setOnClickListener {
            deleteListener.invoke(item.id)
        }

        holder.ivEditEquipment.setOnClickListener {
            updateListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return equipmentItems.size
    }

    class ViewHolder(binding: EquipmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val llEquipment = binding.llEquipment
        val tvEquipmentName = binding.tvEquipmentName
        val tvEquipmentOwnName = binding.tvEquipmentOwnName
        val tvEquipmentAmount = binding.tvEquipmentAmount
        val tvEquipmentStorageLocation = binding.tvEquipmentStorageLocation
        val tvEquipmentYear = binding.tvEquipmentYear
        val tvEquipmentSerialNumber = binding.tvEquipmentSerialNumber
        val tvEquipmentInventoryNumber = binding.tvEquipmentInventoryNumber
        val tvEquipmentTechnicalReviewDate = binding.tvEquipmentTechnicalReviewDate
        val tvEquipmentEnginePower = binding.tvEquipmentEnginePower
        val ivDeleteEquipment = binding.ivDeleteEquipment
        val ivEditEquipment = binding.ivEditEquipment

    }
}