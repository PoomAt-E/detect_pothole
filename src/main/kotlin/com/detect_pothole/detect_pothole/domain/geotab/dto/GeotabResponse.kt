package com.detect_pothole.detect_pothole.domain.geotab.dto

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab

class GeotabResponse(
        val id: Long,
        val placename: String,
        val area: List<*>,
) {

    companion object{
        fun of(geotab: Geotab): GeotabResponse {
            return GeotabResponse(
                    geotab.id!!,
                    geotab.placename!!,
                    geotab.area!!.coordinates.toList().map { listOf(it.x, it.y) },
            )
        }
    }
}