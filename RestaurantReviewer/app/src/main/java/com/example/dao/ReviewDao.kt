package com.example.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.model.Review

@Dao
interface ReviewDao: BaseDao<Review> {
    @Query("SELECT * FROM review")
    override fun getAll(): List<Review>

    @Query("SELECT * FROM review WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Review>

    @Query("SELECT * FROM review WHERE id == id")
    override fun findById(id: String): Review

    @Insert
    override fun insertAll(vararg models: Review)

    @Delete
    override fun delete(model: Review)
}