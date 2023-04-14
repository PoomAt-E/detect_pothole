package com.detect_pothole.detect_pothole.global.error

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    // Global
    INVALID_INPUT_VALUE(400, "G001", "유효하지 않은 입력값입니다."),
    INTERNAL_SERVER_ERROR(500, "G002", "서버 내부 오류입니다."),
    FILE_CONVERT_ERROR(500, "G003", "파일 변환 오류입니다.")
}