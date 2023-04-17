package com.detect_pothole.detect_pothole.domain.pothole.dto

import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import org.springframework.data.geo.Point
import java.math.BigDecimal

class PotholeResponse(
        val id: Long,
        val geotabId: Long,
        val xacc: BigDecimal,
        val yacc: BigDecimal,
        val zacc: BigDecimal,
        val videoURL: String,
        val imageURL: String,
        val point: Point,
        val state: Int,
) {
    companion object {
        fun of(pothole: Pothole): PotholeResponse {
            return PotholeResponse(
                    pothole.id!!,
                    pothole.geotabId!!.id!!,
                    pothole.xacc!!,
                    pothole.yacc!!,
                    pothole.zacc!!,
                    pothole.videoURL!!,
                    pothole.imageURL!!,
                    pothole.point!!,
                    pothole.state
            )
        }
    }
}