package com.batch14.orderservice.rest

import com.batch14.orderservice.domain.dto.response.BaseResponse
import com.batch14.orderservice.domain.dto.response.ResGetProductDto
import com.batch14.orderservice.domain.dto.response.ResGetUserDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "product-service",
    path = "/product-service"
)
interface ProductManagementClient {
    @GetMapping("/v1/products/{id}")
    fun getProductById(
        @PathVariable(name = "id") idProduct: Int
    ): ResponseEntity<BaseResponse<ResGetProductDto>>

    @GetMapping("/v1/data-source/products/by-ids")
    fun getProductByIds(
        @RequestParam(name = "ids", required = true) productIds: List<Int>
    ): ResponseEntity<BaseResponse<List<ResGetProductDto>>>

}