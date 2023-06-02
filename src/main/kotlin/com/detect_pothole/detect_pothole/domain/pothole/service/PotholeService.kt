package com.detect_pothole.detect_pothole.domain.pothole.service

import com.detect_pothole.detect_pothole.domain.geotab.exception.GeotabNotFoundException
import com.detect_pothole.detect_pothole.domain.geotab.repository.GeotabRepository
import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import com.detect_pothole.detect_pothole.domain.pothole.exception.PotholeNotFoundException
import com.detect_pothole.detect_pothole.domain.pothole.repository.PotholeRepository
import com.detect_pothole.detect_pothole.global.ConvertUtill
import com.detect_pothole.detect_pothole.global.geojson.GeoJsonDTO
import com.detect_pothole.detect_pothole.global.geojson.GeoJsonElementDTO
import com.detect_pothole.detect_pothole.global.geojson.GeometryDTO
import com.detect_pothole.detect_pothole.global.geojson.Properties
import com.detect_pothole.detect_pothole.global.result.ResultCode
import com.detect_pothole.detect_pothole.global.result.ResultResponse
import com.detect_pothole.detect_pothole.infra.gcp.GcpStorageService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.sql.Timestamp

@Service
class PotholeService(
        private val potholeRepository: PotholeRepository,
        private val geotabRepository: GeotabRepository,
        private val gcpStorageService: GcpStorageService
) {
    @Transactional
    fun register(
            geotabId: Long,
            xacc: Double,
            yacc: Double,
            zacc: Double,
            x: Double,
            y: Double,
            image: MultipartFile
    ): ResultResponse {
        val imageUrl = gcpStorageService.uploadImageToGCS(image)

        val pothole = Pothole().apply {
            this.geotabId = geotabRepository.findById(geotabId).orElseThrow{ GeotabNotFoundException() }
            this.xacc = BigDecimal.valueOf(xacc)
            this.yacc = BigDecimal.valueOf(yacc)
            this.zacc = BigDecimal.valueOf(zacc)
            this.point = ConvertUtill.getPoint(x, y)
//            this.videoURL = videoUrl
            this.imageURL = imageUrl
            this.state = "A"  // TODO: 추후 ML 서버에서 받아온 값으로 변경
            this.regDt = Timestamp(System.currentTimeMillis())
            this.modDt = Timestamp(System.currentTimeMillis())
        }
        potholeRepository.save(pothole)
        return ResultResponse(
                ResultCode.POTHOLE_REGISTER_SUCCESS
        )
    }

    fun findPotholeById(
            id: Long
    ): ResultResponse {
        val pothole = potholeRepository.findById(id).orElseThrow { PotholeNotFoundException() }
        return ResultResponse(
                ResultCode.POTHOLE_SEARCH_SUCCESS,
                getGeoJson(listOf(pothole))
        )
    }

    fun findPotholeByGeotabId(
            geotabId: Long
    ): ResultResponse {
        val geotab = geotabRepository.findById(geotabId).orElseThrow { GeotabNotFoundException() }
        val potholeList = geotab.potholeList
        return ResultResponse(
                ResultCode.POTHOLE_SEARCH_SUCCESS,
                getGeoJson(potholeList)
        )
    }

    fun findAllPothole(): ResultResponse {
        val potholeList = potholeRepository.findAll()
        return ResultResponse(
                ResultCode.POTHOLE_SEARCH_SUCCESS,
                getGeoJson(potholeList)
        )
    }

    fun update(
        potholeId: Long,
        xacc: Double,
        yacc: Double,
        zacc: Double,
        x: Double,
        y: Double
    ): ResultResponse {
        val pothole = potholeRepository.findById(potholeId).orElseThrow { PotholeNotFoundException() }
//        if(video != null) {
//            val videoUrl = gcpStorageService.uploadVideoToGCS(video)
//            pothole.videoURL = videoUrl
//        }
        pothole.apply {
            this.xacc = BigDecimal.valueOf(xacc)
            this.yacc = BigDecimal.valueOf(yacc)
            this.zacc = BigDecimal.valueOf(zacc)
            this.point = ConvertUtill.getPoint(x, y)
            this.modDt = Timestamp(System.currentTimeMillis())
        }
        potholeRepository.save(pothole)

        return ResultResponse(
                ResultCode.POTHOLE_UPDATE_SUCCESS
        )
    }

    private fun getGeoJson(
        potholes: List<Pothole>
    ): GeoJsonDTO {
        val geoJsonElementList = potholes.map {pothole ->
            GeoJsonElementDTO(
                geometry = GeometryDTO("Point", listOf(pothole.point!!.x, pothole.point!!.y)),
                properties = Properties(code = pothole.id.toString(), name = "pothole"+pothole.id, numberOfPothole = "X")
            )
        }
        return GeoJsonDTO(geoJsonElementList)
    }
}