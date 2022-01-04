package com.rafalropel.mobileosp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "finances-table")
data class FinancesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var lp: Int,
    val dateOfSave: String,
    val financesType: String,
    var bankIncome: String,
    var bankOutcome: String,
    var bankStatus: String,
    var cashIncome: String,
    var cashOutcome: String,
    var cashStatus: String,
    var normalMembersMoney: String,
    var supportMembersMoney: String,
    var grant: String,
    var bankInterest: String,
    var materials: String,
    var services: String,
    var bop: String,
    var bussinesTravel: String,
    var other: String,
    var comments: String
)
