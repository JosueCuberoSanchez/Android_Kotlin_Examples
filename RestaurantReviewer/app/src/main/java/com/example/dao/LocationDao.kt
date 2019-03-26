package com.example.dao

import android.arch.persistence.room.*
import com.example.model.Location

@Dao
interface LocationDao: BaseDao<Location> {
    @Query("SELECT * FROM location")
    override fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Location>

    @Query("SELECT * FROM location WHERE id == :id")
    override fun findById(id: String): Location

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg model: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg models: List<Location>)

    @Delete
    override fun delete(model: Location)
}