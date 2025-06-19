package com.batch14.orderservice.controller

import com.batch14.orderservice.domain.dto.response.ResGetPaymentDto
import com.batch14.orderservice.service.MasterOrderService
import com.batch14.orderservice.service.MasterPaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/payments")
class MasterPaymentController(
    private val masterPaymentService: MasterPaymentService
) {
    @GetMapping("/{id}")
    fun getPaymentById(
        @PathVariable(name = "id") idPayment: Int
    ): ResponseEntity<ResGetPaymentDto> {
        return ResponseEntity.ok(
            masterPaymentService.findPaymentById(idPayment)
        )
    }
}