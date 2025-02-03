package com.example.testasdgroup.data.networking.service

import com.google.gson.annotations.SerializedName

data class FlightDto(
    @SerializedName("flight_number")
    val flightNumber: Int,
    val name: String,
    val rocket: String,
    val details: String?,
    @SerializedName("date_utc")
    val dateUtc: String,
    @SerializedName("date_unix")
    val dateUnix: Long,
    val success: Boolean?,
    val links: LinksDto,
    val launch: String,
    val webCastUrl: String?
)

data class LinksDto(
    val patch: PatchDto
)

data class PatchDto(
    val small: String?,
    val large: String?
)