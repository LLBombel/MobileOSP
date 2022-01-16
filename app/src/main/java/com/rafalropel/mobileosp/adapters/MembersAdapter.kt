package com.rafalropel.mobileosp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafalropel.mobileosp.databinding.MemberItemBinding
import com.rafalropel.mobileosp.entities.MembersEntity

class MembersAdapter(
    private val memberItems: ArrayList<MembersEntity>,
//private val updateListener:(id:Int)->Unit,
    private val deleteListener: (id: Int) -> Unit
) : RecyclerView.Adapter<MembersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MemberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = memberItems[position]

        holder.tvMemberName.text = item.namesurname

        holder.ivEdit.setOnClickListener {
            //updateListener.invoke(item.id)
        }

        holder.ivDelete.setOnClickListener {
            deleteListener.invoke(item.id)
        }

    }

    override fun getItemCount(): Int {
        return memberItems.size
    }

    class ViewHolder(binding: MemberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val llMembers = binding.llMembers
        val tvMemberName = binding.tvMemberName
        val ivEdit = binding.ivEditMember
        val ivDelete = binding.ivDeleteMember
    }
}