package com.detect_pothole.detect_pothole.domain.pothole.dto

import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import org.locationtech.jts.geom.Point

class PotholeResponse(
        val id: Long,
        val geotabId: Long,
//        val xacc: Double,
//        val yacc: Double,
//        val zacc: Double,
//        val videoURL: String,
        val imageURL: String,
        val x: Double,
        val y: Double,
        val state: String,
) {
    companion object {
        fun of(pothole: Pothole): PotholeResponse {
            return PotholeResponse(
                    pothole.id!!,
                    pothole.geotabId!!.id!!,
//                    pothole.xacc!!.toDouble(),
//                    pothole.yacc!!.toDouble(),
//                    pothole.zacc!!.toDouble(),
//                    pothole.videoURL!!,
                    pothole.imageURL!!,
                    pothole.point!!.x,
                    pothole.point!!.y,
                    pothole.state!!
            )
        }
    }
}