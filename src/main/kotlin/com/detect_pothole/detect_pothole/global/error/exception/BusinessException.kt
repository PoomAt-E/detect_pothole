package com.detect_pothole.detect_pothole.global.error.exception

import com.detect_pothole.detect_pothole.global.error.ErrorCode


open class BusinessException(errorCode: ErrorCode) : RuntimeException(errorCode.message) {
    val errorCode: ErrorCode

    init {
        this.errorCode = errorCode
    }
}