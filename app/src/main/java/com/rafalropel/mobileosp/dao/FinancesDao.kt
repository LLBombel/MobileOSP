package com.rafalropel.mobileosp.dao

import androidx.room.*
import com.rafalropel.mobileosp.entities.FinancesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FinancesDao {

    @Insert
    suspend fun insert(financesEntity: FinancesEntity)

    @Update
    suspend fun update(financesEntity: FinancesEntity)

    @Delete
    suspend fun delete(financesEntity: FinancesEntity)

    @Query("SELECT * FROM `finances-table`")
    fun fetchAllFinances(): Flow<List<FinancesEntity>>


    @Query("SELECT * FROM `finances-table` where id=:id")
    fun fetchFinancesById(id: Int): Flow<FinancesEntity>

}