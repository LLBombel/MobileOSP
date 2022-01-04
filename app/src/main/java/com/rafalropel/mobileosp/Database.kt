package com.rafalropel.mobileosp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rafalropel.mobileosp.dao.*
import com.rafalropel.mobileosp.entities.*

@Database(
    entities = [MembersEntity::class, CarsEntity::class, DuesEntity::class, FinancesEntity::class,
        EquipmentEntity::class], version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun membersDao(): MembersDao
    abstract fun carsDao(): CarsDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun duesDao(): DuesDao
    abstract fun financesDao(): FinancesDao


    companion object {
        @Volatile
        private var INSTANCE: com.rafalropel.mobileosp.Database? = null

        fun getInstance(context: Context): com.rafalropel.mobileosp.Database {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, com.rafalropel.mobileosp.Database::class.java,
                        "mobileosp_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}