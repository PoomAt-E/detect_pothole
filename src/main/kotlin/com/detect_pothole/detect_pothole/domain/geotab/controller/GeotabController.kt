package com.detect_pothole.detect_pothole.domain.geotab.controller

import com.detect_pothole.detect_pothole.domain.geotab.dto.GeotabAreaUpdateRequest
import com.detect_pothole.detect_pothole.domain.geotab.dto.GeotabNameUpdateRequest
import com.detect_pothole.detect_pothole.domain.geotab.dto.GeotabRegistrationRequest
import com.detect_pothole.detect_pothole.domain.geotab.service.GeotabService
import com.detect_pothole.detect_pothole.global.result.ResultResponse
import org.springframework.data.geo.Point
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/geotab")
class GeotabController(
        private val geotabService: GeotabService
) {
    @PostMapping("/register")
    fun register(
            @RequestBody geotabRegistrationRequest: GeotabRegistrationRequest
    ): ResultResponse {
        return geotabService.register(
                geotabRegistrationRequest.areaName,
                geotabRegistrationRequest.area
        )
    }

    @GetMapping("/search/id/{id}")
    fun findGeotabById(
            @PathVariable id: Long
    ): ResultResponse {
        return geotabService.findGeotabById(id)
    }

    @GetMapping("/search/areaName/{areaName}")
    fun findGeotabByAreaName(
            @PathVariable areaName: String
    ): ResultResponse {
        return geotabService.findGeotabByAreaName(areaName)
    }

    @PostMapping("/search/point")
    fun findGeotabByPoint(
            @RequestBody point: Point
    ): ResultResponse {
        return geotabService.findGeotabByPoint(point)
    }

    @GetMapping("/search/all")
    fun findAllGeotab(): ResultResponse {
        return geotabService.findAllGeotab()
    }

    @PostMapping("/update/name")
    fun updateGeotabName(
            @RequestBody geotabNameUpdateRequest: GeotabNameUpdateRequest
    ): ResultResponse {
        return geotabService.updateGeotabName(
                geotabNameUpdateRequest.id,
                geotabNameUpdateRequest.placename
        )
    }

    @PostMapping("/update/area")
    fun updateGeotabArea(
            @RequestBody geotabAreaUpdateRequest: GeotabAreaUpdateRequest
    ): ResultResponse {
        return geotabService.updateGeotabArea(
                geotabAreaUpdateRequest.id,
                geotabAreaUpdateRequest.area
        )
    }
}