package com.detect_pothole.detect_pothole.domain.pothole.dto

import org.locationtech.jts.geom.Point

data class PotholeUpdateRequest (
        val potholeId: Long,
        val xacc: Double,
        val yacc: Double,
        val zacc: Double,
        val x: Double,
        val y: Double
)