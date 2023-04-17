package com.detect_pothole.detect_pothole.domain.geotab.dto

import org.springframework.data.geo.Point

data class GeotabAreaUpdateRequest(
        var id: Long,
        var area: List<Point>
)
