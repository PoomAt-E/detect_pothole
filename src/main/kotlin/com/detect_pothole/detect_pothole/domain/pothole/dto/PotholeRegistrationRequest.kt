package com.detect_pothole.detect_pothole.domain.pothole.dto

import org.springframework.data.geo.Point
import java.math.BigDecimal

data class PotholeRegistrationRequest(
        val geotabId: Long,
        val xacc: BigDecimal,
        val yacc: BigDecimal,
        val zacc: BigDecimal,
        val point: Point
)