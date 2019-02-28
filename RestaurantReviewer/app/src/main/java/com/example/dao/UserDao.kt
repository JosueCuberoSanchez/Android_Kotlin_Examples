package com.example.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.model.User

@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM user")
    override fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:ids)")
    override fun loadAllByIds(ids: IntArray): List<User>

    @Query("SELECT * FROM user WHERE id == id")
    override fun findById(id: String): User

    @Insert
    override fun insertAll(vararg models: User)

    @Delete
    override fun delete(model: User)
}