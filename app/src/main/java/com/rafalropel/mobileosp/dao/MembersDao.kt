package com.rafalropel.mobileosp.dao

import androidx.room.*
import com.rafalropel.mobileosp.entities.MembersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MembersDao {

    @Insert
    suspend fun insert(membersDao: MembersDao)

    @Update
    suspend fun update(membersDao: MembersDao)

    @Delete
    suspend fun delete(membersDao: MembersDao)

    @Query("SELECT * FROM `members_table`")
    fun fetchAllMembers(): Flow<List<MembersEntity>>

    @Query("SELECT * FROM `members_table` where id=:id")
    fun fetchMembersById(id: Int): Flow<MembersEntity>

}