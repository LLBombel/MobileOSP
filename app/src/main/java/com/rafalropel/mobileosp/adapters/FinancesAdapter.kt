package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.FinancesItemBinding
import com.rafalropel.mobileosp.entities.FinancesEntity

class FinancesAdapter(
    private val financesList: ArrayList<FinancesEntity>,
    private val deleteListener: (id: Int) -> Unit
) : RecyclerView.Adapter<FinancesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FinancesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = financesList[position]

        holder.tvLP.text = item.lp

        holder.ivDeleteFinances.setOnClickListener {
            deleteListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return financesList.size
    }

    class ViewHolder(binding: FinancesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvLP = binding.tvLP
        val ivEditFinances = binding.ivEditFinances
        val ivDeleteFinances = binding.ivDeleteFinances
        val llFinances = binding.llFinances
    }
}