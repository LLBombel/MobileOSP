package com.rafalropel.mobileosp.dao

import androidx.room.*
import com.rafalropel.mobileosp.entities.MembersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MembersDao {

    @Insert
    suspend fun insert(membersEntity: MembersEntity)

    @Update
    suspend fun update(membersEntity: MembersEntity)

    @Delete
    suspend fun delete(membersEntity: MembersEntity)

    @Query("SELECT * FROM `members_table`")
    fun fetchAllMembers(): Flow<List<MembersEntity>>

    @Query("SELECT * FROM `members_table` where id=:id")
    fun fetchMembersById(id: Int): Flow<MembersEntity>

}