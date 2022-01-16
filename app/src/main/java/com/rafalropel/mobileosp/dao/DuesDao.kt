package com.rafalropel.mobileosp.dao

import androidx.room.*
import com.rafalropel.mobileosp.entities.DuesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DuesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(duesEntity: DuesEntity)

    @Update
    suspend fun update(duesEntity: DuesEntity)

    @Delete
    suspend fun delete(duesEntity: DuesEntity)

    @Query("SELECT * FROM `dues-table`")
    fun fetchAllDues(): Flow<List<DuesEntity>>

    @Query("SELECT * FROM `dues-table` where id=:id")
    fun fetchDuesById(id: Int): Flow<DuesEntity>

}