package com.example.dao

interface BaseDao<T> {
    fun getAll(): List<T>
    fun loadAllByIds(ids: IntArray): List<T>
    fun findById(id: String): T
    fun insert(vararg model: T)
    fun insertAll(vararg models: List<T>)
    fun delete(model: T)
}