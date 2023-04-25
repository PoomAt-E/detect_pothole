package com.detect_pothole.detect_pothole.global.geojson

class GeoJsonElementDTO (
        val geometry: GeometryDTO
) {
    val type: String = "Feature"
}