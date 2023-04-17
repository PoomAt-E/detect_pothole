package com.detect_pothole.detect_pothole.domain.geotab.entity

import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import jakarta.persistence.*
import org.springframework.data.geo.Polygon
import java.sql.Timestamp

@Entity
@Table(name = "geotab")
class Geotab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "placename", nullable = false)
    var placename: String? = null

    @Column(name = "area", nullable = false)
    var area: Polygon? = null

    @Column(name = "regDt", nullable = false)
    var regDt: Timestamp? = null

    @Column(name = "modDt", nullable = false)
    var modDt: Timestamp? = null

    @OneToMany(mappedBy = "geotabId", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    var potholeList: MutableList<Pothole> = mutableListOf()
}