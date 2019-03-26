package com.example.dao

import android.arch.persistence.room.*
import com.example.model.Photo

@Dao
interface PhotoDao: BaseDao<Photo> {
    @Query("SELECT * FROM photo")
    override fun getAll(): List<Photo>

    @Query("SELECT * FROM photo WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Photo>

    @Query("SELECT * FROM photo WHERE id == :id")
    override fun findById(id: String): Photo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg model: Photo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg models: List<Photo>)

    @Delete
    override fun delete(model: Photo)
}