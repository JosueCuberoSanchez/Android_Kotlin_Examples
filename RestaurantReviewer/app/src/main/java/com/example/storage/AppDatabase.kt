package com.example.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.dao.*
import com.example.model.*

@Database(entities = [User::class,
                      Restaurant::class,
                      Review::class,
                      Photo::class,
                      Location::class],
          version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun restaurantDao(): RestaurantDao
    abstract fun reviewDao(): ReviewDao
    abstract fun photoDao(): PhotoDao
    abstract fun locationDao(): LocationDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase::class.java, "restaurants-db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}