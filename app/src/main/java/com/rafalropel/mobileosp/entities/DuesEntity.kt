package com.rafalropel.mobileosp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dues-table")
data class DuesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val duesMemberName: String="",
    var declarationSignDate: String="",
    var declaredMoney: String="",
    val duesYear: String="",
    val duesDate: String="",
    val duesMoney: String=""

)
