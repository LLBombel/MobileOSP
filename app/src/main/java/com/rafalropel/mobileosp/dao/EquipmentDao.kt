package com.rafalropel.mobileosp.dao

import androidx.room.*
import com.rafalropel.mobileosp.entities.EquipmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {

@Insert
suspend fun insert(equipmentEntity: EquipmentEntity)

@Update
suspend fun update(equipmentEntity: EquipmentEntity)

@Delete
suspend fun delete(equipmentEntity: EquipmentEntity)

@Query("SELECT * FROM `equipment-table`")
fun fetchAllEquipment(): Flow<List<EquipmentEntity>>

@Query("SELECT * FROM `equipment-table` where id=:id")
fun fetchEquipmentById(id: Int): Flow<EquipmentEntity>

}