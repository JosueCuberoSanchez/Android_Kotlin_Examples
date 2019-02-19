package com.example.model

data class Restaurant(val id: String, val name: String, val location: Location,
                      val url: String, val reviews: Array<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Restaurant

        if (id != other.id) return false
        if (name != other.name) return false
        if (location != other.location) return false
        if (url != other.url) return false
        if (!reviews.contentEquals(other.reviews)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + reviews.contentHashCode()
        return result
    }
}
