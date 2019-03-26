package com.example.dao

import android.arch.persistence.room.*
import com.example.model.Restaurant

@Dao
interface RestaurantDao: BaseDao<Restaurant> {
    @Query("SELECT * FROM restaurant")
    override fun getAll(): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id == :id")
    override fun findById(id: String): Restaurant

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg model: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg models: List<Restaurant>)

    @Delete
    override fun delete(model: Restaurant)
}