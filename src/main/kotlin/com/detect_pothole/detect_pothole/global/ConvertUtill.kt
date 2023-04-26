package com.detect_pothole.detect_pothole.global

import com.detect_pothole.detect_pothole.domain.pothole.dto.PointDTO
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Polygon

class ConvertUtill {
    companion object {
        private val factory = GeometryFactory()
        fun getPoint(
            x: Double,
            y: Double
        ): Point {
//            val pointWKT = String.format("POINT(%f %f)", x, y)
//            val point = WKTReader().read(pointWKT) as Point
            return factory.createPoint(Coordinate(x, y))
        }

        fun getPolygon(
            area: List<PointDTO>
        ): Polygon {
            val coordinateList = area.map {
                Coordinate(it.x, it.y)
            }

            val linear = factory.createLinearRing(coordinateList.toTypedArray())
            val polygon = factory.createPolygon(linear)
            return polygon
        }
    }
}