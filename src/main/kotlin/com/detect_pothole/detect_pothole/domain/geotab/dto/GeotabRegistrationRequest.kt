package com.detect_pothole.detect_pothole.domain.geotab.dto

import org.springframework.data.geo.Point

data class GeotabRegistrationRequest(
        var areaName: String,
        var area: List<Point>,
)
