package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafalropel.mobileosp.adapters.MembersAdapter
import com.rafalropel.mobileosp.dao.MembersDao
import com.rafalropel.mobileosp.databinding.ActivityMembersBinding
import com.rafalropel.mobileosp.entities.MembersEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private lateinit var binding: ActivityMembersBinding

class MembersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMembersBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        val membersDao = (application as App).db.membersDao()

        binding.fabAddMembers.setOnClickListener {
            showMembersInput()
        }

        binding.btAddMemberCancel.setOnClickListener {
            cancelMembersInput()
        }

        binding.btAddMember.setOnClickListener {
            addMember(membersDao)
        }


        lifecycleScope.launch {
            membersDao.fetchAllMembers().collect {
                val list = ArrayList(it)
                displayMembers(list, membersDao)
            }
        }

        setContentView(binding.root)
    }


    private fun showMembersInput() {
        binding.llMembersInput.visibility = View.VISIBLE
        binding.rvMembersList.visibility = View.GONE
    }

    private fun cancelMembersInput(){
        binding.llMembersInput.visibility = View.GONE
        binding.rvMembersList.visibility = View.VISIBLE
    }

    private fun addMember(membersDao: MembersDao){
        val name = binding.etMemberName.text.toString()
        val rank = binding.etRank.text.toString()
        val dateOfBirth = binding.etDateOfBirth.text.toString()
        val placeOfBirth = binding.etPlaceOfBirth.text.toString()
        val fatherName = binding.etFatherName.text.toString()
        val PESEL = binding.etPESEL.text.toString()
        val joinDate = binding.etDateOfJoin.text.toString()
        val typeOfMember = binding.etTypeOfMember.text.toString()
        val email = binding.etEmail.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val street = binding.etStreet.text.toString()
        val houseNumber = binding.etHouseNumber.text.toString()
        val apartmentNumber = binding.etApartmentNumber.text.toString()
        val city = binding.etCity.text.toString()



        if(name.isNotEmpty()){
            lifecycleScope.launch{
                membersDao.insert(MembersEntity(namesurname = name, rank = rank, dateOfBirth = dateOfBirth,
                placeOfBirth = placeOfBirth, fatherName = fatherName, pesel = PESEL, joinDate = joinDate, typeOfMember = typeOfMember,
                email = email, phoneNumber = phoneNumber, street = street, houseNumber = houseNumber, apartmentNumber =
                apartmentNumber, city = city))

                binding.rvMembersList.visibility = View.VISIBLE
                binding.llMembersInput.visibility = View.GONE
            }

        }else{
            Toast.makeText(this, "Przynajmniej imię i nazwisko musi być wypełnione", Toast.LENGTH_LONG).show()
        }





    }

    private fun deleteMember(id: Int, membersDao: MembersDao){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Czy na pewno usunąć członka?")
        builder.setCancelable(false)
        builder.setPositiveButton("Tak"){dialogInterface, _ ->
            lifecycleScope.launch {
                membersDao.delete(MembersEntity(id))
                Toast.makeText(applicationContext, "Usunięto członka", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Nie"){dialogInterface, _->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun displayMembers(membersList: ArrayList<MembersEntity>, membersDao: MembersDao){
        if(membersList.isNotEmpty()){
            val membersAdapter = MembersAdapter(membersList
            ) { deleteId ->
                deleteMember(deleteId, membersDao)
            }

            binding.rvMembersList.layoutManager = LinearLayoutManager(this)
            binding.rvMembersList.adapter = membersAdapter
            binding.rvMembersList.visibility = View.VISIBLE
        }
    }
}