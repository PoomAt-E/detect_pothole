package com.detect_pothole.detect_pothole.domain.pothole.controller

import com.detect_pothole.detect_pothole.domain.pothole.dto.PotholeRegistrationRequest
import com.detect_pothole.detect_pothole.domain.pothole.dto.PotholeUpdateRequest
import com.detect_pothole.detect_pothole.domain.pothole.service.PotholeService
import com.detect_pothole.detect_pothole.global.result.ResultResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/pothole")
class PotholeController(
        private val potholeService: PotholeService
) {
    @PostMapping("/register")
    fun register(
            @RequestBody potholeRegistrationRequest: PotholeRegistrationRequest,
    ): ResultResponse {
        return potholeService.register(
                potholeRegistrationRequest.geotabId,
                potholeRegistrationRequest.xacc,
                potholeRegistrationRequest.yacc,
                potholeRegistrationRequest.zacc,
                potholeRegistrationRequest.x,
                potholeRegistrationRequest.y,
        )
    }

    @PutMapping("/img/{id}")
    fun setImage(
            @PathVariable id: Long,
            @RequestPart image: MultipartFile
    ): ResultResponse {
        return potholeService.registerImage(id, image)
    }

    @GetMapping("/search/id/{id}")
    fun findPotholeById(
            @PathVariable id: Long
    ): ResultResponse {
        return potholeService.findPotholeById(id)
    }

    @GetMapping("/search/geotabId/{geotabId}")
    fun findPotholeByGeotabId(
            @PathVariable geotabId: Long
    ): ResultResponse {
        return potholeService.findPotholeByGeotabId(geotabId)
    }

    @GetMapping("/search/all")
    fun findAllPothole(): ResultResponse {
        return potholeService.findAllPothole()
    }

    @PostMapping("/update")
    fun update(
            @RequestBody potholeUpdateRequest: PotholeUpdateRequest,
    ): ResultResponse {
        return potholeService.update(
                potholeUpdateRequest.potholeId,
                potholeUpdateRequest.xacc,
                potholeUpdateRequest.yacc,
                potholeUpdateRequest.zacc,
                potholeUpdateRequest.x,
                potholeUpdateRequest.y,
        )
    }
}