package com.example.dao

import android.arch.persistence.room.*
import com.example.model.User

@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM user")
    override fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<User>

    @Query("SELECT * FROM user WHERE id == :id")
    override fun findById(id: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg model: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertAll(vararg models: List<User>)

    @Delete
    override fun delete(model: User)
}