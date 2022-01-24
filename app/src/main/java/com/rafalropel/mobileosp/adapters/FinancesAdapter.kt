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
        holder.tvFinancesDate.text = item.dateOfSave
        holder.tvFinancesType.text = item.financesType
        holder.tvFinancesBankIncome.text = item.bankIncome
        holder.tvFinancesBankOutcome.text = item.bankOutcome
        holder.tvFinancesBankStatus.text = item.bankStatus
        holder.tvFinancesCashIncome.text = item.cashIncome
        holder.tvFinancesCashOutcome.text = item.cashOutcome
        holder.tvFinancesCashStatus.text = item.cashStatus
        holder.tvFinancesNormalMemberMoney.text = item.normalMembersMoney
        holder.tvFinancesSupportMemberMoney.text = item.supportMembersMoney
        holder.tvFinancesGrant.text = item.grant
        holder.tvFinancesBankInterest.text = item.bankInterest
        holder.tvFinancesMaterials.text = item.materials
        holder.tvFinancesServices.text = item.services
        holder.tvFinancesBOP.text = item.bop
        holder.tvFinancesBussinessTravel.text = item.bussinesTravel
        holder.tvFinancesOther.text = item.other
        holder.tvFinancesComments.text = item.comments


        holder.ivDeleteFinances.setOnClickListener {
            deleteListener.invoke(item.id)
        }
    }

    override fun getItemCount(): Int {
        return financesList.size
    }

    class ViewHolder(binding: FinancesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvLP = binding.tvLP
        val tvFinancesDate = binding.tvFinancesDate
        val tvFinancesType = binding.tvFinancesType
        val tvFinancesBankIncome = binding.tvFinancesBankIncome
        val tvFinancesBankOutcome = binding.tvFinancesBankOutcome
        val tvFinancesBankStatus = binding.tvFinancesBankStatus
        val tvFinancesCashIncome = binding.tvFinancesCashIncome
        val tvFinancesCashOutcome = binding.tvFinancesCashOutcome
        val tvFinancesCashStatus = binding.tvFinancesBankStatus
        val tvFinancesNormalMemberMoney = binding.tvFinancesNormalMemberMoney
        val tvFinancesSupportMemberMoney = binding.tvFinancesSupportMemberMoney
        val tvFinancesGrant = binding.tvFinancesGrant
        val tvFinancesBankInterest = binding.tvFinancesBankInterest
        val tvFinancesMaterials = binding.tvFinancesMaterials
        val tvFinancesServices = binding.tvFinancesServices
        val tvFinancesBOP = binding.tvFinancesBOP
        val tvFinancesBussinessTravel = binding.tvFinancesBussinessTravel
        val tvFinancesOther = binding.tvFinancesOther
        val tvFinancesComments = binding.tvFinancesComments
        val ivEditFinances = binding.ivEditFinances
        val ivDeleteFinances = binding.ivDeleteFinances
        val llFinances = binding.llFinances
    }
}