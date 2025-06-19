package com.batch14.orderservice.domain.dto.response

import java.sql.Timestamp

data class ResGetOrderDto(
    val id: Int,
    val amount: Int,
    val isPaid: Boolean,
    val paymentMethod: String?,
    val username: String?,
    val productName: String?,
    val createdAt: Timestamp?
)
