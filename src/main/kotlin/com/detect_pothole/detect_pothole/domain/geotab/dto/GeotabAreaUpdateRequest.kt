package com.detect_pothole.detect_pothole.domain.geotab.dto

import com.detect_pothole.detect_pothole.domain.pothole.dto.PointDTO

data class GeotabAreaUpdateRequest(
        var id: Long,
        var area: List<PointDTO>
)
