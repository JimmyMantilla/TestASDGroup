package com.example.testasdgroup.data.persistent.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FlightEntity::class], version = 1, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightDao
}