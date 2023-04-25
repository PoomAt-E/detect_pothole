package com.detect_pothole.detect_pothole.global.geojson

class GeoJsonDTO (
        val features: List<GeoJsonElementDTO>
) {
    val type: String = "FeatureCollection"
}