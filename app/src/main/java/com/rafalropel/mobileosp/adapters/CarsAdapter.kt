package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.CarItemBinding
import com.rafalropel.mobileosp.entities.CarsEntity

class CarsAdapter(
    private val carItems: ArrayList<CarsEntity>,
    private val deleteListener: (id: Int) -> Unit,
    private val updateListener: (id: Int) -> Unit
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
        holder.tvCarOwnName.text = item.carOwnName
        holder.tvCarCodeName.text = item.codeName
        holder.tvCarNumber.text = item.carNumber
        holder.tvCarAmount.text = item.amount
        holder.tvCarType.text = item.type
        holder.tvCarTechnicalReviewDate.text = item.technicalReviewDate
        holder.tvCarInsuranceDate.text = item.insuranceDate
        holder.tvCarWeight.text = item.weight
        holder.tvCarEnginePower.text = item.enginePower
        holder.tvCarTank.text = item.tank
        holder.tvCarFoam.text = item.foam
        holder.tvCarChassis.text = item.chassis
        holder.tvCarChassisYear.text = item.chassisYear
        holder.tvCarAmountOfPeople.text = item.amountOfPeople
        holder.tvCarStorageLocation.text = item.storageLocation

        holder.ivDeleteCar.setOnClickListener {
            deleteListener.invoke(item.id)

        }

        holder.ivEditCar.setOnClickListener {
            updateListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return carItems.size
    }

    class ViewHolder(binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val llCars = binding.llCars
        val tvCarName = binding.tvCarName
        val tvCarOwnName = binding.tvCarOwnName
        val tvCarCodeName = binding.tvCarCodeName
        val tvCarNumber = binding.tvCarNumber
        val tvCarAmount = binding.tvCarAmount
        val tvCarType = binding.tvCarType
        val tvCarTechnicalReviewDate = binding.tvCarTechnicalReviewDate
        val tvCarInsuranceDate = binding.tvCarInsuranceDate
        val tvCarWeight = binding.tvCarWeight
        val tvCarEnginePower = binding.tvCarEnginePower
        val tvCarTank = binding.tvCarTank
        val tvCarFoam = binding.tvCarFoam
        val tvCarChassis = binding.tvCarChassis
        val tvCarChassisYear = binding.tvCarChassisYear
        val tvCarAmountOfPeople = binding.tvCarAmountOfPeople
        val tvCarStorageLocation = binding.tvCarStorageLocation
        val ivDeleteCar = binding.ivDeleteCar
        val ivEditCar = binding.ivEditCar
    }
}