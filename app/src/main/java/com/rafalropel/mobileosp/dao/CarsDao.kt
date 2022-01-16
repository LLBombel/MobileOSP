package com.rafalropel.mobileosp.dao

import androidx.room.*
import com.rafalropel.mobileosp.entities.CarsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(carsEntity: CarsEntity)

    @Update
    suspend fun update(carsEntity: CarsEntity)

    @Delete
    suspend fun delete(carsEntity: CarsEntity)

    @Query("SELECT * FROM `cars_table`")
    fun fetchALlCars(): Flow<List<CarsEntity>>

    @Query("SELECT * FROM `cars_table` where id=:id")
    fun fetchCarsById(id: Int): Flow<CarsEntity>

}