package com.detect_pothole.detect_pothole.domain.pothole.service

import com.detect_pothole.detect_pothole.domain.geotab.exception.GeotabNotFoundException
import com.detect_pothole.detect_pothole.domain.geotab.repository.GeotabRepository
import com.detect_pothole.detect_pothole.domain.pothole.dto.PotholeResponse
import com.detect_pothole.detect_pothole.domain.pothole.entity.Pothole
import com.detect_pothole.detect_pothole.domain.pothole.exception.PotholeNotFoundException
import com.detect_pothole.detect_pothole.domain.pothole.repository.PotholeRepository
import com.detect_pothole.detect_pothole.global.result.ResultCode
import com.detect_pothole.detect_pothole.global.result.ResultResponse
import com.detect_pothole.detect_pothole.infra.gcp.GcpStorageService
import jakarta.transaction.Transactional
import org.springframework.data.geo.Point
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
            xacc: BigDecimal,
            yacc: BigDecimal,
            zacc: BigDecimal,
            point: Point,
            video: MultipartFile
    ): ResultResponse {
        val videoUrl = gcpStorageService.uploadFileToGCS(video)

        //TODO: ML 서버 api 호출
        val img: MultipartFile? = null
        val imageUrl = gcpStorageService.uploadFileToGCS(img!!)

        val pothole = Pothole().apply {
            this.geotabId = geotabRepository.findById(geotabId).orElseThrow{ GeotabNotFoundException() }
            this.xacc = xacc
            this.yacc = yacc
            this.zacc = zacc
            this.point = point
            this.videoURL = videoUrl
            this.imageURL = imageUrl
            this.state = 0  // TODO: 추후 ML 서버에서 받아온 값으로 변경
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
                PotholeResponse.of(pothole)
        )
    }

    fun findPotholeByGeotabId(
            geotabId: Long
    ): ResultResponse {
        val geotab = geotabRepository.findById(geotabId).orElseThrow { GeotabNotFoundException() }
        val potholeList = geotab.potholeList.map { PotholeResponse.of(it) }
        return ResultResponse(
                ResultCode.POTHOLE_SEARCH_SUCCESS,
                potholeList
        )
    }

    fun findAllPothole(): ResultResponse {
        val potholeList = potholeRepository.findAll().map { PotholeResponse.of(it) }
        return ResultResponse(
                ResultCode.POTHOLE_SEARCH_SUCCESS,
                potholeList
        )
    }

}