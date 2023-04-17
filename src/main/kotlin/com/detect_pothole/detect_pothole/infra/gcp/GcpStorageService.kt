package com.detect_pothole.detect_pothole.infra.gcp

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class GcpStorageService(
        private val storage: Storage,
        @Value("\${spring.cloud.gcp.storage.bucket}")
        private val bucketName: String
) {
    fun uploadFileToGCS(imageFile: MultipartFile): String {
        val randUUID = UUID.randomUUID().toString()
        val fileEx = getFileEx(imageFile.originalFilename!!)
        val blobInfo = storage.create(

                BlobInfo.newBuilder(bucketName, "user-profile/$randUUID")
                        .setContentDisposition("/user-profile")
                        .setContentType("image/$fileEx")
                        .build(),
                imageFile.inputStream
        )

        // TODO : Multipart file의 타입이 이미지인지 비디오인지에 따라 유동적으로 대응 가능하도록 수정해야 함.
        return Codes.VIDEO_PATH + randUUID + "." + fileEx
    }

    fun getFileEx(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1).lowercase()
    }
}