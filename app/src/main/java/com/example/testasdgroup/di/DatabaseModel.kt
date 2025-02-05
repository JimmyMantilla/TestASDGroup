package com.example.testasdgroup.di

import android.content.Context
import androidx.room.Room
import com.example.testasdgroup.data.persistent.db.FlightDao
import com.example.testasdgroup.data.persistent.db.FlightDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FlightDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FlightDatabase::class.java,
            "flight_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFlightDao(database: FlightDatabase): FlightDao {
        return database.flightDao()
    }
}