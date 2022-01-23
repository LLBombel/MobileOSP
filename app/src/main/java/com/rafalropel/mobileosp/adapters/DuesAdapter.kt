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
        holder.tvDueNameSurname.text = item.duesMemberName
        holder.tvDueDeclarationSignDate.text = item.declarationSignDate
        holder.tvDueDeclaredMoney.text = item.declaredMoney
        holder.tvDueYear.text = item.duesYear
        holder.tvDueDate.text = item.duesDate
        holder.tvDueMoney.text = item.duesMoney

        holder.ivDeleteDue.setOnClickListener {
            deleteListener.invoke(item.id)
        }

    }

    override fun getItemCount(): Int {
        return duesList.size
    }

    class ViewHolder(binding: DueItemBinding) : RecyclerView.ViewHolder(binding.root) {


        val tvDueNameSurname = binding.tvDueNameSurname
        val tvDueDeclarationSignDate = binding.tvDueDeclarationSignDate
        val tvDueDeclaredMoney = binding.tvDueDeclaredMoney
        val tvDueYear = binding.tvDueYear
        val tvDueDate = binding.tvDueDate
        val tvDueMoney = binding.tvDueMoney
        val llDues = binding.llDues
        val ivEditDue = binding.ivEditDue
        val ivDeleteDue = binding.ivDeleteDue
    }

}