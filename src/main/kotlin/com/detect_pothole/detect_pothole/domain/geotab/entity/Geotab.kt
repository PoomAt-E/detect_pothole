package com.detect_pothole.detect_pothole.domain.geotab.entity

import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import jakarta.persistence.*
import org.locationtech.jts.geom.Polygon
import java.sql.Timestamp

@Entity
@Table(name = "geotab")
class Geotab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "place_name")
    var placename: String? = null

    @Column(name = "area", columnDefinition = "GEOMETRY")
    var area: Polygon? = null

    @Column(name = "reg_dt")
    var regDt: Timestamp? = null

    @Column(name = "mod_dt")
    var modDt: Timestamp? = null

    @OneToMany(mappedBy = "geotabId", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    var potholeList: MutableList<Pothole> = mutableListOf()
}