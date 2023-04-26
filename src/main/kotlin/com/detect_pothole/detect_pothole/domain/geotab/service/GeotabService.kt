package com.detect_pothole.detect_pothole.domain.geotab.service

import com.detect_pothole.detect_pothole.domain.geotab.entity.Geotab
import com.detect_pothole.detect_pothole.domain.geotab.exception.GeotabNotFoundException
import com.detect_pothole.detect_pothole.domain.geotab.dto.GeotabResponse
import com.detect_pothole.detect_pothole.domain.geotab.exception.GeotabNameDuplicatedException
import com.detect_pothole.detect_pothole.domain.geotab.repository.GeotabRepository
import com.detect_pothole.detect_pothole.domain.pothole.dto.PointDTO
import com.detect_pothole.detect_pothole.global.ConvertUtill
import com.detect_pothole.detect_pothole.global.result.ResultCode
import com.detect_pothole.detect_pothole.global.result.ResultResponse
import jakarta.transaction.Transactional
import org.locationtech.jts.geom.*
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class GeotabService(
        private val geotabRepository: GeotabRepository
) {
    @Transactional
    fun register(
            areaName: String,
            area: List<PointDTO>
    ): ResultResponse {
        if(geotabRepository.existsByPlacename(areaName)) throw GeotabNameDuplicatedException()

        val geotab = Geotab().apply {
            this.placename = areaName
            this.area = ConvertUtill.getPolygon(area)
            this.regDt = Timestamp(System.currentTimeMillis())
            this.modDt = Timestamp(System.currentTimeMillis())
        }
        geotabRepository.save(geotab)
        return ResultResponse(
                ResultCode.GEOTAB_REGISTER_SUCCESS
        )
    }
    fun findGeotabById(
            id: Long
    ): ResultResponse {
        val geotab = geotabRepository.findById(id).orElseThrow { GeotabNotFoundException() }
        return ResultResponse(
                ResultCode.GEOTAB_SEARCH_SUCCESS,
                GeotabResponse.of(geotab)
        )
    }
    fun findGeotabByAreaName(
            areaName: String
    ): ResultResponse {
        val geotab = geotabRepository.findByPlacename(areaName).orElseThrow { GeotabNotFoundException() }
        return ResultResponse(
                ResultCode.GEOTAB_SEARCH_SUCCESS,
                GeotabResponse.of(geotab)
        )
    }
    fun findGeotabByPoint(
            pointDTO: PointDTO
    ): ResultResponse {
        val point = ConvertUtill.getPoint(pointDTO.x, pointDTO.y)
        val geotabList = geotabRepository.findAll()
        val containedGeotabList = ArrayList<Geotab>()
        for (geotab in geotabList) {
            if(findContainingPolygon(point, geotab.area!!)) {
                containedGeotabList.add(geotab)
            }
        }

        if (containedGeotabList.isEmpty()) throw GeotabNotFoundException()

        return ResultResponse(
                ResultCode.GEOTAB_SEARCH_SUCCESS,
                containedGeotabList.map { GeotabResponse.of(it) }  // 여러개가 있지는 않을 것으로 예상
        )
    }
    fun findAllGeotab(): ResultResponse {
        val geotabList = geotabRepository.findAll().map {
            GeotabResponse.of(it)
        }
        return ResultResponse(
                ResultCode.GEOTAB_SEARCH_SUCCESS,
                geotabList
        )
    }
    fun updateGeotabName(
            id: Long,
            areaName: String
    ): ResultResponse {
        val geotab = geotabRepository.findById(id).orElseThrow { GeotabNotFoundException() }
        if (geotabRepository.existsByPlacename(areaName)) throw GeotabNameDuplicatedException()

        geotab.placename = areaName
        geotab.modDt = Timestamp(System.currentTimeMillis())
        geotabRepository.save(geotab)
        return ResultResponse(
                ResultCode.GEOTAB_UPDATE_SUCCESS
        )
    }
    fun updateGeotabArea(
            id: Long,
            area: List<PointDTO>
    ): ResultResponse {
        val geotab = geotabRepository.findById(id).orElseThrow { GeotabNotFoundException() }

        geotab.area =  ConvertUtill.getPolygon(area)
        geotab.modDt = Timestamp(System.currentTimeMillis())
        geotabRepository.save(geotab)
        return ResultResponse(
                ResultCode.GEOTAB_UPDATE_SUCCESS
        )
    }
    private fun findContainingPolygon(point: Point, polygon: Geometry): Boolean {
        if (polygon.contains(point)) {
            return true
        }
        return false
    }
}