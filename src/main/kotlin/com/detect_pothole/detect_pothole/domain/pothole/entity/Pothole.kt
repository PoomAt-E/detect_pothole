package com.detect_pothole.detect_pothole.domain.pothole.entity

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab
import jakarta.persistence.*
import org.locationtech.jts.geom.Point
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
@Table(name = "pothole")
class Pothole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gid")
    var geotabId: Geotab? = null

    @Column(name = "x_acc")
    var xacc: BigDecimal? = null

    @Column(name = "y_acc")
    var yacc: BigDecimal? = null

    @Column(name = "z_acc")
    var zacc: BigDecimal? = null

    @Column(name = "video_url")
    var videoURL: String? = null

    @Column(name = "image_url")
    var imageURL: String? = null

    @Column(name = "point", columnDefinition = "GEOMETRY")
    var point: Point? = null

    @Column(name = "state")
    var state: String? = null

    @Column(name = "reg_dt")
    var regDt: Timestamp? = null

    @Column(name = "mod_dt")
    var modDt: Timestamp? = null
}