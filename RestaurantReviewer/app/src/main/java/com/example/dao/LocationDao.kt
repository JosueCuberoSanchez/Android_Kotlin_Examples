package com.example.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.model.Location

@Dao
interface LocationDao: BaseDao<Location> {
    @Query("SELECT * FROM location")
    override fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Location>

    @Query("SELECT * FROM location WHERE id == id")
    override fun findById(id: String): Location

    @Insert
    override fun insertAll(vararg models: Location)

    @Delete
    override fun delete(model: Location)
}