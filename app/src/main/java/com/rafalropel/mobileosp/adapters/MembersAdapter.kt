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
        holder.tvMemberRank.text = item.rank
        holder.tvMemberDateOfBirth.text = item.dateOfBirth
        holder.tvMemberPlaceOfBirth.text = item.placeOfBirth
        holder.tvMemberFatherName.text = item.fatherName
        holder.tvMemberPESEL.text = item.pesel
        holder.tvMemberDateOfJoin.text = item.joinDate
        holder.tvMemberType.text = item.typeOfMember
        holder.tvMemberEmail.text = item.email
        holder.tvMemberPhoneNumber.text = item.phoneNumber
        holder.tvMemberCity.text = item.city
        holder.tvMemberStreet.text = item.street
        holder.tvMemberHouseNumber.text = item.houseNumber
        holder.tvMembersApartmentNumber.text = item.apartmentNumber

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
        val tvMemberRank = binding.tvMemberRank
        val tvMemberDateOfBirth = binding.tvMemberDateOfBirth
        val tvMemberPlaceOfBirth = binding.tvMemberPlaceOfBirth
        val tvMemberFatherName = binding.tvMemberFatherName
        val tvMemberPESEL = binding.tvMemberPESEL
        val tvMemberDateOfJoin = binding.tvMemberDateOfJoin
        val tvMemberType = binding.tvMemberType
        val tvMemberEmail = binding.tvMemberEmail
        val tvMemberPhoneNumber = binding.tvMemberPhoneNumber
        val tvMemberCity = binding.tvMemberCity
        val tvMemberStreet = binding.tvMemberStreet
        val tvMemberHouseNumber = binding.tvMemberHouseNumber
        val tvMembersApartmentNumber = binding.tvMemberApartmentNumber
        val ivEdit = binding.ivEditMember
        val ivDelete = binding.ivDeleteMember
    }
}