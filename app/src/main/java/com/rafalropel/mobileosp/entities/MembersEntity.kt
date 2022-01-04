package com.rafalropel.mobileosp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "members_table")
data class MembersEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var namesurname: String = "",
    var rank: String = "",
    val dateOfBirth: String = "",
    val placeOfBirth: String = "",
    val fatherName: String = "",
    val pesel: String = "",
    val joinDate: String = "",
    var typeOfMember: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var city: String = "",
    var street: String = "",
    var houseNumber: String = "",
    var apartmentNumber: String = ""

)
