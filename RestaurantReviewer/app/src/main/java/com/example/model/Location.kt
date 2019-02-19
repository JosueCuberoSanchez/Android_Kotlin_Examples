package com.example.model

data class Location(val lat: String, val address: String, val lgn: String,
                    val photos: Array<Photo>, val postalCode: String, val province: String,
                    val city: String, val country: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (lat != other.lat) return false
        if (address != other.address) return false
        if (lgn != other.lgn) return false
        if (!photos.contentEquals(other.photos)) return false
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
        result = 31 * result + photos.contentHashCode()
        result = 31 * result + postalCode.hashCode()
        result = 31 * result + province.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }
}