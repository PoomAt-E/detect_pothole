package com.detect_pothole.detect_pothole.domain.pothole.repository

import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import org.springframework.data.jpa.repository.JpaRepository

interface PotholeRepository: JpaRepository<Pothole, Long> {
}