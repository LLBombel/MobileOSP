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

    }

    private fun cancelMembersInput() {
        binding.llMembersInput.visibility = View.GONE
        binding.rvMembersList.visibility = View.VISIBLE
    }

    private fun addMember(membersDao: MembersDao) {
        binding.apply {
            val name = etMemberName.text.toString()
            val rank = etRank.text.toString()
            val dateOfBirth = etDateOfBirth.text.toString()
            val placeOfBirth = etPlaceOfBirth.text.toString()
            val fatherName = etFatherName.text.toString()
            val PESEL = etPESEL.text.toString()
            val joinDate = etDateOfJoin.text.toString()
            val typeOfMember = etTypeOfMember.text.toString()
            val email = etEmail.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val street = etStreet.text.toString()
            val houseNumber = etHouseNumber.text.toString()
            val apartmentNumber = etApartmentNumber.text.toString()
            val city = etCity.text.toString()

            if (name.isNotEmpty()) {
                lifecycleScope.launch {
                    membersDao.insert(
                        MembersEntity(
                            namesurname = name,
                            rank = rank,
                            dateOfBirth = dateOfBirth,
                            placeOfBirth = placeOfBirth,
                            fatherName = fatherName,
                            pesel = PESEL,
                            joinDate = joinDate,
                            typeOfMember = typeOfMember,
                            email = email,
                            phoneNumber = phoneNumber,
                            street = street,
                            houseNumber = houseNumber,
                            apartmentNumber =
                            apartmentNumber,
                            city = city
                        )
                    )

                    rvMembersList.visibility = View.VISIBLE
                    llMembersInput.visibility = View.GONE



                        etMemberName.text?.clear()
                        etRank.text?.clear()
                        etDateOfBirth.text?.clear()
                        etPlaceOfBirth.text?.clear()
                        etFatherName.text?.clear()
                        etPESEL.text?.clear()
                        etDateOfJoin.text?.clear()
                        etTypeOfMember.text?.clear()
                        etEmail.text?.clear()
                        etPhoneNumber.text?.clear()
                        etStreet.text?.clear()
                        etHouseNumber.text?.clear()
                        etApartmentNumber.text?.clear()
                        etCity.text?.clear()

                }

            } else {
                Toast.makeText(
                    this@MembersActivity,
                    "Przynajmniej imię i nazwisko musi być wypełnione",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }

    private fun deleteMember(id: Int, membersDao: MembersDao) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Czy na pewno usunąć członka?")
        builder.setCancelable(false)
        builder.setPositiveButton("Tak") { dialogInterface, _ ->
            lifecycleScope.launch {
                membersDao.delete(MembersEntity(id))
                Toast.makeText(applicationContext, "Usunięto członka", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Nie") { dialogInterface, _ ->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun displayMembers(membersList: ArrayList<MembersEntity>, membersDao: MembersDao) {
        if (membersList.isNotEmpty()) {
            val membersAdapter = MembersAdapter(
                membersList
            ) { deleteId ->
                deleteMember(deleteId, membersDao)
            }

            binding.rvMembersList.layoutManager = LinearLayoutManager(this)
            binding.rvMembersList.adapter = membersAdapter
            binding.rvMembersList.visibility = View.VISIBLE
        }
    }
}