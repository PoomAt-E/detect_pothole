package com.detect_pothole.detect_pothole.domain.geotab.dto

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab
import org.springframework.data.geo.Point


class GeotabResponse(
        val id: Long,
        val placename: String,
        val area: List<Point>,
        val regDt: String,
        val modDt: String
) {

    companion object{
        fun of(geotab: Geotab): GeotabResponse {
            return GeotabResponse(
                    geotab.id!!,
                    geotab.placename!!,
                    geotab.area!!.points,
                    geotab.regDt.toString(),
                    geotab.modDt.toString()
            )
        }
    }
}