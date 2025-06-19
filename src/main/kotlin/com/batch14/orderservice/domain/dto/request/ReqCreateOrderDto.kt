package com.batch14.orderservice.domain.dto.request

data class ReqCreateOrderDto(
    val productId: Int,
    val paymentId: Int,
    val isPaid: Boolean? = false
)
