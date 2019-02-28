package com.example.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.model.Restaurant

@Dao
interface RestaurantDao: BaseDao<Restaurant> {
    @Query("SELECT * FROM restaurant")
    override fun getAll(): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<Restaurant>

    @Query("SELECT * FROM restaurant WHERE id == :id")
    override fun findById(id: String): Restaurant

    @Insert
    override fun insertAll(vararg models: Restaurant)

    @Delete
    override fun delete(model: Restaurant)
}