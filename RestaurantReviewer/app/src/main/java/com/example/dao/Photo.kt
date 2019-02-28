package com.example.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.model.Photo

@Dao
interface PhotoDao: BaseDao<Photo> {
    @Query("SELECT * FROM photo")
    override fun getAll(): List<Photo>

    @Query("SELECT * FROM photo WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Photo>

    @Query("SELECT * FROM photo WHERE id == id")
    override fun findById(id: String): Photo

    @Insert
    override fun insertAll(vararg models: Photo)

    @Delete
    override fun delete(model: Photo)
}