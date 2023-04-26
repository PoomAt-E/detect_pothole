package com.detect_pothole.detect_pothole.domain.pothole.dto


data class PotholeRegistrationRequest(
        val geotabId: Long,
        val xacc: Double,
        val yacc: Double,
        val zacc: Double,
        val x: Double,
        val y: Double
)