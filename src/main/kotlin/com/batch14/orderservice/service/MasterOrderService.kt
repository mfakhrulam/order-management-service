package com.batch14.orderservice.service

import com.batch14.orderservice.domain.dto.request.ReqCreateOrderDto
import com.batch14.orderservice.domain.dto.response.ResGetOrderDto

interface MasterOrderService {
    fun findAllOrders(): List<ResGetOrderDto>
    fun createOrder(req: ReqCreateOrderDto): ResGetOrderDto
}