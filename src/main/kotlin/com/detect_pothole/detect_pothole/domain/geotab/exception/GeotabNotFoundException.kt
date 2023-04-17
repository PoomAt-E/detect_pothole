package com.detect_pothole.detect_pothole.domain.geotab.exception

import com.detect_pothole.detect_pothole.global.error.ErrorCode
import com.detect_pothole.detect_pothole.global.error.exception.BusinessException

class GeotabNotFoundException(): BusinessException(
        ErrorCode.GEOTAB_NOT_FOUND
)