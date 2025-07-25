package com.batch14.orderservice.domain.dto.response

import java.util.UUID

data class BaseResponse<T>(
    val reqId: UUID? = UUID.randomUUID(),
    val status: String = "T",
    val message: String? = "Berhasil",
    val error: Any? = null,
    val data: T? = null
)