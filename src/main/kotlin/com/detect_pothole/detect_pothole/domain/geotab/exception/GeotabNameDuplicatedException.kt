package com.detect_pothole.detect_pothole.domain.geotab.exception

import com.detect_pothole.detect_pothole.global.error.ErrorCode
import com.detect_pothole.detect_pothole.global.error.exception.BusinessException

class GeotabNameDuplicatedException(): BusinessException(
        ErrorCode.GEOTAB_NAME_DUPLICATED
)