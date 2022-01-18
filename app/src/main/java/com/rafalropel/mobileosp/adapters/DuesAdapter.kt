package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.DueItemBinding
import com.rafalropel.mobileosp.entities.DuesEntity

class DuesAdapter(
    private val duesList: ArrayList<DuesEntity>,
    private val deleteListener: (id: Int) -> Unit
) :
    RecyclerView.Adapter<DuesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuesAdapter.ViewHolder {
        return ViewHolder(
            DueItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DuesAdapter.ViewHolder, position: Int) {
        val item = duesList[position]
        holder.tvDueName.text = item.duesMemberName

        holder.ivDeleteDue.setOnClickListener {
            deleteListener.invoke(item.id)
        }

    }

    override fun getItemCount(): Int {
        return duesList.size
    }

    class ViewHolder(binding: DueItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val tvDueName = binding.tvDueName
        val llDues = binding.llDues
        val ivEditDue = binding.ivEditDue
        val ivDeleteDue = binding.ivDeleteDue
    }

}