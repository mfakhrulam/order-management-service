package com.batch14.orderservice.service.impl

import com.batch14.orderservice.domain.dto.response.ResGetPaymentDto
import com.batch14.orderservice.exception.CustomException
import com.batch14.orderservice.repository.MasterPaymentRepository
import com.batch14.orderservice.service.MasterPaymentService
import org.springframework.stereotype.Service

@Service
class MasterPaymentServiceImpl(
    private val masterPaymentRepository: MasterPaymentRepository
): MasterPaymentService {
    override fun findPaymentById(paymentId: Int): ResGetPaymentDto {
        val rawData = masterPaymentRepository.findById(paymentId).orElseThrow {
            CustomException(
                "Payment Method $paymentId tidak ditemukan",
                400
            )
        }
        val result = ResGetPaymentDto(
            id = rawData.id,
            paymentMethod = rawData.paymentMethod
        )
        return result
    }

}