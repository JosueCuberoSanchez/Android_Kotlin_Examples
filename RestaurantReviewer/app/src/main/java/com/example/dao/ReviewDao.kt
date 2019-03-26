package com.example.dao

import android.arch.persistence.room.*
import com.example.model.Review

@Dao
interface ReviewDao: BaseDao<Review> {
    @Query("SELECT * FROM review")
    override fun getAll(): List<Review>

    @Query("SELECT * FROM review WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Review>

    @Query("SELECT * FROM review WHERE id == :id")
    override fun findById(id: String): Review

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg model: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg models: List<Review>)

    @Delete
    override fun delete(model: Review)
}