package com.batch14.orderservice.controller

import com.batch14.orderservice.domain.dto.request.ReqCreateOrderDto
import com.batch14.orderservice.domain.dto.response.BaseResponse
import com.batch14.orderservice.domain.dto.response.ResGetOrderDto
import com.batch14.orderservice.service.MasterOrderService
import org.springframework.http.ResponseEntity
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/orders")
class MasterOrderController(
    private val masterOrderService: MasterOrderService
) {
    @GetMapping("/")
    fun getAllOrders(): ResponseEntity<BaseResponse<List<ResGetOrderDto>>> {
        return ResponseEntity.ok(
            BaseResponse(
                data = masterOrderService.findAllOrders()
            )
        )
    }

    @PostMapping("/")
    fun createOrder(
        @Valid @RequestBody req: ReqCreateOrderDto
    ): ResponseEntity<BaseResponse<ResGetOrderDto>> {
        return ResponseEntity.ok(
            BaseResponse(
                data = masterOrderService.createOrder(req)
            )
        )
    }
}