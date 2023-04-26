package com.detect_pothole.detect_pothole.global.error

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    // Global
    INVALID_INPUT_VALUE(400, "G001", "유효하지 않은 입력값입니다."),
    INTERNAL_SERVER_ERROR(500, "G002", "서버 내부 오류입니다."),
    FILE_CONVERT_ERROR(500, "G003", "파일 변환 오류입니다."),

    // Geotab
    GEOTAB_NOT_FOUND(400, "GEO001", "지역을 찾을 수 없습니다."),
    GEOTAB_NAME_DUPLICATED(400, "GEO002", "이미 존재하는 지역명입니다."),

    // Pothole
    POTHOLE_NOT_FOUND(400, "POT001", "포트홀을 찾을 수 없습니다."),
    POTHOLE_ALREADY_EXIST(400, "POT002", "이미 존재하는 포트홀입니다."),
}