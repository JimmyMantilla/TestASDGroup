package com.example.testasdgroup.data.persistent.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class FlightEntity(
    @PrimaryKey val flightNumber: Int,
    val rocketName: String,
    val rocketType: String,
    val missionName: String,
    val launch: String,
    val details: String,
    val patchUrl: String,
    val patchUrlLarge: String,
    val dateUtc: String,
    val dateUnix: Long,
    val webCastUrl: String
)