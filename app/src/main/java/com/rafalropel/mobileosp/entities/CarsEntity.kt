package com.rafalropel.mobileosp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class CarsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val carName: String = "",
    var carOwnName: String= "",
    var codeName: String= "",
    var carNumber: String= "",
    var amount: String= "",
    val type: String= "",
    var technicalReviewDate: String= "",
    var insuranceDate: String= "",
    var weight: String= "",
    val enginePower: String= "",
    val tank: String= "",
    val foam: String= "",
    val chassis: String= "",
    val chassisYear: String= "",
    val amountOfPeople: String= "",
    var storageLocation: String= ""

)
