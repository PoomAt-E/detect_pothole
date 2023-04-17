package com.detect_pothole.detect_pothole.domain.pothole.entity

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab
import jakarta.persistence.*
import org.springframework.data.geo.Point
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
class Pothole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geotabId")
    var geotabId: Geotab? = null

    @Column(name = "X_acc", nullable = false)
    var xacc: BigDecimal? = null

    @Column(name = "Y_acc", nullable = false)
    var yacc: BigDecimal? = null

    @Column(name = "Z_acc", nullable = false)
    var zacc: BigDecimal? = null

    @Column(name = "videoUrl", nullable = false)
    var videoURL: String? = null

    @Column(name = "imageURL", nullable = false)
    var imageURL: String? = null

    @Column(name = "point", nullable = false)
    var point: Point? = null

    @Column(name = "state", nullable = false)
    var state: Int = 0

    @Column(name = "regDt", nullable = false)
    var regDt: Timestamp? = null

    @Column(name = "modDt", nullable = false)
    var modDt: Timestamp? = null
}