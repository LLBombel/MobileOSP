package com.rafalropel.mobileosp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment-table")
data class EquipmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val equipmentName: String = "",
    var equipmentOwnName: String = "",
    var equipmentAmount: String = "",
    var equipmentStorageLocation: String = "",
    val equipmentYear: String = "",
    val equipmentSerialNumber: String = "",
    var equipmentInventoryNumber: String = "",
    var equipmentTechnicalReviewDate: String = "",
    val equipmentEnginePower: String = ""

)
