package com.detect_pothole.detect_pothole.domain.pothole.dto

import org.springframework.data.geo.Point
import java.math.BigDecimal

data class PotholeRegistrationRequest(
        val geotabId: Long,
        val xacc: Double,
        val yacc: Double,
        val zacc: Double,
        val point: Point
)