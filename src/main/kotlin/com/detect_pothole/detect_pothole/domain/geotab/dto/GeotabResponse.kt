package com.detect_pothole.detect_pothole.domain.geotab.dto

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab
import org.locationtech.jts.geom.Polygon


class GeotabResponse(
        val id: Long,
        val placename: String,
        val area: Polygon,
        val regDt: String,
        val modDt: String
) {

    companion object{
        fun of(geotab: Geotab): GeotabResponse {
            return GeotabResponse(
                    geotab.id!!,
                    geotab.placename!!,
                    geotab.area!!,
                    geotab.regDt.toString(),
                    geotab.modDt.toString()
            )
        }
    }
}