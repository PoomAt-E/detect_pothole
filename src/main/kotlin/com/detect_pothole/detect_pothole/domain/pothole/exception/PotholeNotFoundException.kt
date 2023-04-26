package com.detect_pothole.detect_pothole.domain.pothole.exception

import com.detect_pothole.detect_pothole.global.error.ErrorCode
import com.detect_pothole.detect_pothole.global.error.exception.BusinessException

class PotholeNotFoundException : BusinessException(
        ErrorCode.POTHOLE_NOT_FOUND
) {
}