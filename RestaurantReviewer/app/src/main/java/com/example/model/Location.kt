package com.example.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Location(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "latitude") val lat: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "longitude") val lgn: String,
    @ColumnInfo(name = "photos") val photos: List<Photo>,
    @ColumnInfo(name = "postal_code") val postalCode: String,
    @ColumnInfo(name = "province") val province: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "country") val country: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (lat != other.lat) return false
        if (address != other.address) return false
        if (lgn != other.lgn) return false
        if (postalCode != other.postalCode) return false
        if (province != other.province) return false
        if (city != other.city) return false
        if (country != other.country) return false

        return true
    }

    override fun hashCode(): Int {
        var result = lat.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + lgn.hashCode()
        result = 31 * result + postalCode.hashCode()
        result = 31 * result + province.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }
}