package com.example.resources

import com.example.model.Location
import com.example.model.Photo
import com.example.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class TypeConverter {
    companion object {
        @android.arch.persistence.room.TypeConverter
        @JvmStatic
        fun storedStringToReviewArray(data: String?): List<Review> {
            return genericStoredStringToObjectArray(data)
        }

        @android.arch.persistence.room.TypeConverter
        @JvmStatic
        fun reviewArrayToString(myObjects: List<Review>): String {
            return genericObjectArrayToStoredString(myObjects)
        }

        @android.arch.persistence.room.TypeConverter
        @JvmStatic
        fun storedStringToPhotoArray(data: String?): List<Photo> {
            return genericStoredStringToObjectArray(data)
        }

        @android.arch.persistence.room.TypeConverter
        @JvmStatic
        fun photoArrayToString(myObjects: List<Photo>): String {
            return genericObjectArrayToStoredString(myObjects)
        }

        @android.arch.persistence.room.TypeConverter
        @JvmStatic
        fun storedStringToLocation(data: String?): Location {
            return genericStoredStringToObject(data)
        }

        @android.arch.persistence.room.TypeConverter
        @JvmStatic
        fun locationObjectToString(myObjects: Location): String {
            return genericObjectToStoredString(myObjects)
        }

        fun <T> genericStoredStringToObjectArray(data: String?): List<T> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<T>>() {

            }.type
            return gson.fromJson<List<T>>(data, listType)
        }

        fun <T> genericStoredStringToObject(data: String?): T {
            val gson = Gson()
            val listType = object : TypeToken<T>() {

            }.type
            return gson.fromJson<T>(data, listType)
        }

        fun <T> genericObjectArrayToStoredString(myObjects: List<T>): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }

        fun <T> genericObjectToStoredString(myObjects: T): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }
    }
}