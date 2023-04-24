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
            xacc: Double,
            yacc: Double,
            zacc: Double,
            point: Point,
            video: MultipartFile
    ): ResultResponse {
        val videoUrl = gcpStorageService.uploadVideoToGCS(video)

        //TODO: ML 서버 api 호출
        val img: MultipartFile? = null
        val imageUrl = gcpStorageService.uploadImageToGCS(img!!)

        val pothole = Pothole().apply {
            this.geotabId = geotabRepository.findById(geotabId).orElseThrow{ GeotabNotFoundException() }
            this.xacc = BigDecimal.valueOf(xacc)
            this.yacc = BigDecimal.valueOf(yacc)
            this.zacc = BigDecimal.valueOf(zacc)
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

    fun update(
        potholeId: Long,
        xacc: Double,
        yacc: Double,
        zacc: Double,
        point: Point,
        video: MultipartFile?
    ): ResultResponse {
        val pothole = potholeRepository.findById(potholeId).orElseThrow { PotholeNotFoundException() }
        if(video != null) {
            val videoUrl = gcpStorageService.uploadVideoToGCS(video)
            pothole.videoURL = videoUrl
            //TODO : ML 서버 api 호출, 반환 받은 정보 바인딩
        }
        pothole.apply {
            this.xacc = BigDecimal.valueOf(xacc)
            this.yacc = BigDecimal.valueOf(yacc)
            this.zacc = BigDecimal.valueOf(zacc)
            this.point = point
            this.modDt = Timestamp(System.currentTimeMillis())
        }
        potholeRepository.save(pothole)

        return ResultResponse(
                ResultCode.POTHOLE_UPDATE_SUCCESS
        )
    }
}