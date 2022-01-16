package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.CarItemBinding
import com.rafalropel.mobileosp.entities.CarsEntity

class CarsAdapter(
    private val carItems: ArrayList<CarsEntity>,
    private val deleteListener: (id: Int) -> Unit
) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = carItems[position]

        holder.tvCarName.text = item.carName

        holder.ivDeleteCar.setOnClickListener {
            deleteListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return carItems.size
    }

    class ViewHolder(binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val llCars = binding.llCars
        val tvCarName = binding.tvCarName
        val ivDeleteCar = binding.ivDeleteCar
        val ivEditCar = binding.ivEditCar
    }
}