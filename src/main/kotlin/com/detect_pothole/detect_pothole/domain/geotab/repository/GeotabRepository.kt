package com.detect_pothole.detect_pothole.domain.geotab.repository

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface GeotabRepository: JpaRepository<Geotab, Long> {
    fun findByPlacename(placename: String): Optional<Geotab>
    fun existsByPlacename(placename: String): Boolean
}