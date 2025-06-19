package com.batch14.orderservice.service

import com.batch14.orderservice.domain.dto.response.ResGetOrderDto
import com.batch14.orderservice.domain.dto.response.ResGetPaymentDto

interface MasterPaymentService {
    fun findPaymentById(paymentId: Int): ResGetPaymentDto

}