package com.rafalropel.mobileosp

import android.app.Dialog
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
import com.rafalropel.mobileosp.databinding.MemberEditDialogBinding
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
        binding.fabAddMembers.visibility = View.GONE

    }

    private fun cancelMembersInput() {
        binding.llMembersInput.visibility = View.GONE
        binding.rvMembersList.visibility = View.VISIBLE
        binding.fabAddMembers.visibility = View.VISIBLE
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
                    fabAddMembers.visibility = View.VISIBLE



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
            val membersAdapter = MembersAdapter(membersList, {
                updateId ->
                updateMember(updateId, membersDao)
            }, {
                deleteId ->
                deleteMember(deleteId, membersDao)
            })
            binding.rvMembersList.layoutManager = LinearLayoutManager(this)
            binding.rvMembersList.adapter = membersAdapter
            binding.rvMembersList.visibility = View.VISIBLE
        }
    }

    private fun updateMember(id: Int, membersDao: MembersDao) {
        val updateDialog = Dialog(this, R.style.ThemeOverlay_AppCompat)
        updateDialog.setCancelable(false)
        val binding = MemberEditDialogBinding.inflate(layoutInflater)
        updateDialog.setContentView(binding.root)

        lifecycleScope.launch {
            membersDao.fetchMembersById(id).collect {
                binding.etMemberNameSurname.setText(it.namesurname)
                binding.etMemberRank.setText(it.rank)
                binding.etMemberDateOfBirth.setText(it.dateOfBirth)
                binding.etMemberPlaceOfBirth.setText(it.placeOfBirth)
                binding.etMemberFatherName.setText(it.fatherName)
                binding.etMemberPESEL.setText(it.pesel)
                binding.etMemberJoinDate.setText(it.joinDate)
                binding.etMemberType.setText(it.typeOfMember)
                binding.etMemberEmail.setText(it.email)
                binding.etMemberPhoneNumber.setText(it.phoneNumber)
                binding.etMemberCity.setText(it.city)
                binding.etMemberStreet.setText(it.street)
                binding.etMemberHouseNumber.setText(it.houseNumber)
                binding.etMemberApartmentNumber.setText(it.apartmentNumber)

            }
        }
        binding.btnEditMemberSave.setOnClickListener {
            val nameSurname = binding.etMemberNameSurname.text.toString()
            val rank = binding.etMemberRank.text.toString()
            val dateOfBirth = binding.etMemberDateOfBirth.text.toString()
            val placeOfBirth = binding.etMemberPlaceOfBirth.text.toString()
            val fatherName = binding.etMemberFatherName.text.toString()
            val pesel = binding.etMemberPESEL.text.toString()
            val joinDate = binding.etMemberJoinDate.text.toString()
            val type = binding.etMemberType.text.toString()
            val email = binding.etMemberEmail.text.toString()
            val phoneNumber = binding.etMemberPhoneNumber.text.toString()
            val city = binding.etMemberCity.text.toString()
            val street = binding.etMemberStreet.text.toString()
            val houseNumber = binding.etMemberHouseNumber.text.toString()
            val apartmentNumber = binding.etMemberApartmentNumber.text.toString()


            lifecycleScope.launch {
                membersDao.update(
                    MembersEntity(
                        id,
                        nameSurname,
                        rank,
                        dateOfBirth,
                        placeOfBirth,
                        fatherName,
                        pesel,
                        joinDate,
                        type,
                        email,
                        phoneNumber,
                        city,
                        street,
                        houseNumber,
                        apartmentNumber
                    )
                )
                Toast.makeText(applicationContext, "Zaktualizowano członka", Toast.LENGTH_LONG).show()
                updateDialog.dismiss()
            }
        }

        binding.btnEditMemberCancel.setOnClickListener {
            updateDialog.dismiss()
        }
        updateDialog.show()
    }
}